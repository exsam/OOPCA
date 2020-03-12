import beanbags.BeanBagStore;
import beanbags.Check;
import beanbags.Store;

public class TestingClassSam {
  public static void main(String[] args) {
    BeanBagStore testStore = new Store();
    // testStore.addBeanBags(20,"test","banter","10",(short)2012,(byte)12);
    try {
      testStore.loadStoreContents("stocklist.txt");
      // Check.matchingIDs(testBag, Store.stockList);
      Check.validID("00000000");
      /*testStore.addBeanBags(
          6,
          "Manufacturer1",
          "Ryan",
          "000000FF",
          (short) 2020,
          (byte) 7,
          "Testing the mismatch test.");
      testStore.addBeanBags(
          2,
          "Manufacturer1",
          "Ryan",
          "000000FF",
          (short) 2020,
          (byte) 7,
          "Testing the mismatch test.");*/
      //testStore.setBeanBagPrice("000000FF",100);
      //testStore.sellBeanBags(1, "000000FF");
      testStore.reserveBeanBags(2,"000000FF");
      testStore.unreserveBeanBags(5);
      testStore.saveStoreContents("stocklist.txt");
    } catch (Exception e) {
      System.out.println(e);
    }
  }
}
