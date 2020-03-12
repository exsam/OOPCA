import beanbags.BeanBagStore;
import beanbags.Check;
import beanbags.Store;

public class TestingClassSam {
  public static void main(String[] args) {
    BeanBagStore testStore = new Store();
    try {
      testStore.addBeanBags(10,"Man1","Nam1","00000000",(short)2020,(byte)11);
    } catch (Exception e) {
      System.out.println(e);
    }
  }
}
