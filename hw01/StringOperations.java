public class StringOperations {
  public static void main(String[] args) {
    String myName = new String("Chris Carstens");
    System.out.println(myName);

    String myNewName = 'A' + myName.substring(1, myName.length() - 1) + 'Z';
    System.out.println(myNewName);

    String url = new String("www.reddit.com");
    System.out.println(url);
    
    String urlName = url.split("\\.")[1] + "1331";
    System.out.println(urlName);
  }
}
