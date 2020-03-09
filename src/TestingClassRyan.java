import beanbags.*;
import java.io.IOException;


public class TestingClassRyan {
  public static void main(String[] args) {
    //System.out.println("Testing Start");
    Store testing = new Store();
    try {
      testing.loadStoreContents("stocklist.txt");
      testing.reserveBeanBags(2, "test2");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
