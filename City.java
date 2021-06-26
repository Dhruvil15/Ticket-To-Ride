import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;


/* Class representing a City
 */
public class City extends Tree{
  /* lookup table of all cities by name */
  public static HashMap<String, City> cities = new HashMap<String, City>();
  public String name;
  /* adjacent Links */
  public final HashSet<Link> links = new HashSet<Link>();
  /* shortest path distance */
  public int distance;
  /* shortest path parent */
  public Link parent;

  /* construct a City with name nm
   * add to the HashMap of cities
   */
  public City(String nm) {
    name = nm;
    cities.put(name, this);
  }

  /* find a city with name nm in cities
   * return the city if it exists
   * otherwise return a new city with that name
   */
  public static City find(String nm) {
    City p = cities.get(nm);
    if (p == null) {
      p = new City(nm);
      return p;
    }
    return p;
  }

  /* add a link to links
   */
  public void addLink(Link lnk) {
    links.add(lnk);
  }

  /* compare cities by their names
   * return: negative if c1 is alphanumerically less,
   *  0 if names are the same,
   *  positive if c2 is alphanumerically less
   */
  public int compareTo(City p) {
    return name.compareTo(p.name);
  }

  /* return the name of the city
   */
  public String toString() {
    return name;
  }


  /* find a path from this to dest
   * return true if a path is found and false otherwise
   * add the followed Links to routeLinks
   */
  public boolean getLinksTo(City dest, Set<Link> routeLinks) {
    for (Link l : links) {
      if (l.isUsed() && (l != parent)) {
        City child = l.getAdj(this);
        if ((dest == child) || child.getLinksTo(dest, routeLinks)) {
          routeLinks.add(l);
          return true;
        }
      }
    }
    return false;
  }

}
