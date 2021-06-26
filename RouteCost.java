import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class RouteCost {


  public static void main(String[] args) {
    Scanner inp = new Scanner(System.in);
    TreeSet<Link> links = new TreeSet<>();

    //Take the input in one line
    String line = inp.nextLine();

    //Convert into tokens
    StringTokenizer st = new StringTokenizer(line, " ");

    //loop until the user enters done or there are more than 2 inputs
    if ((st.countTokens() == 3 || st.countTokens() == 4 || st.countTokens() == 1)) {

      //run the loop until the user enters "done"
      while (!line.startsWith("done")) {

        //brake the input by the user into tokens and use one-by-one
        st = new StringTokenizer(line, " ");

        City c1 = City.find(st.nextToken());
        String length = st.nextToken();
        int len;
        if(isInteger(length)){
          len = Integer.parseInt(length);
        }
        else{
          System.out.println("Invalid line: "+line);
          break;
        }


        City c2 = City.find(st.nextToken());

        //adding the color value to the links
        if (st.countTokens() == 1) {
          String color = st.nextToken().toLowerCase();
          if (color.equals("red") || color.equals("blue")) {
            Link l1 = new Link(c1, c2, len, color);
          } else {
            System.out.println("Invalid Line: " + line);
            break;
          }
        } else {
          Link l = new Link(c1, c2, len);
        }
        line = inp.nextLine();
      }

      //check in the link list if the cities entered by the user already exists or not
      for (String name = inp.next(); !name.equals("done"); name = inp.next()) {
        City c = City.find(name);
        c.makeTree();
        if (!c.getLinksTo(City.find(inp.next()), links)) {
          System.out.println("Error: Route not found! " + name);
          return;
        }
      }

      //print the lists
      int total = 0;
      System.out.println("The rail network consists of:");
      for (Link l : links) {
        System.out.println("  "+l);
        total += l.getLength();
      }
      System.out.println("The total cost is: " + total);
    } else {
      System.out.println("Invalid line: " + line);
    }
  }

  /**
   * This method checks if the string passed is an integer or a string
   * @param str
   * @return boolean
   */
  public static boolean isInteger(String str) {
    if (str == null) {
      return false;
    }
    int strLength = str.length();
    if (strLength == 0) {
      return false;
    }
    for (int i = 0; i < strLength; i++) {
      char c = str.charAt(i);
      if (c < '0' || c > '9') {
        return false;
      }
    }
    return true;
  }
}

