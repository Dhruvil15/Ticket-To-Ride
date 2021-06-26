import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;

public class Tree{

    /* create a shortest path tree starting from this City
     * uses Dijkstra's shortest paths algorithm
     * set the distance of this City to 0 and others to infinity
     * consider each found City closest to the start
     *   update the best known distance to all adjacent cities
     * postcondition: every City.distance is the shortest distance from this to that City
     * postcondition: every City.parent is the Link before that City in the set of shortest paths
     */
    public void makeTree() {
        Comparator<City> comparator = new CityComparator();
        PriorityQueue<City> pq = new PriorityQueue<>(comparator);
        for (City c : City.cities.values()) {
            if (c != this) {
                c.distance = Integer.MAX_VALUE;
            } else {
                c.distance = 0;
            }
            pq.add(c);
            c.parent = null;
            for (Link l : c.links) {
                l.setUsed(false);
            }
        }

        HashSet<City> tree = new HashSet<>();
        while (!pq.isEmpty()) {
            City city = pq.poll();
            if (city.parent !=  null) {
                city.parent.setUsed(true);
            }
            tree.add(city);

            for (Link l : city.links) {
                City child = l.getAdj(city);
                if (!tree.contains(child)) {
                    int length = l.getLength();

                    if (child.distance > city.distance + length) {
                        pq.remove(child);
                        child.distance = city.distance + length;
                        child.parent = l;
                        pq.add(child);
                    }
                }
            }
        }
    }

}
