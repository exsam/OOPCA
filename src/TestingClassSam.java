import beanbags.BeanBagStore;
import beanbags.Check;
import beanbags.Store;

public class TestingClassSam {
  public static void main(String[] args) {
    BeanBagStore testStore = new Store();
    try {
      testStore.loadStoreContents("test.ser");
      //testStore.addBeanBags(10,"Man1","Nam1","00000020",(short)2020,(byte)11,"test");
      System.out.println(
      testStore.getBeanBagDetails("00000020"));
      testStore.saveStoreContents("test.ser");
      //testStore.empty();
      //testStore.saveStoreContents("test2.ser");
    } catch (Exception e) {
      System.out.println(e);
    }
  }
}
