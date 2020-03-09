import beanbags.*;
import java.io.IOException;


public class TestingClassRyan {
  public static void main(String[] args) {
    System.out.println("Testing Start");
    Store testing = new Store();
    try {
      testing.loadStoreContents("stocklist.txt");
    } catch (Exception e) {
      System.out.println(e);
    }
    for (int i = 0; i < Store.stockList.size(); i++) {
      System.out.println(Store.stockList.get(i).toString());
    }
  }
}
