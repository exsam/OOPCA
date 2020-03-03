import beanbags.*;

import java.io.IOException;

public class TestingClassSam {
  public static void main(String[] args)
      throws IllegalIDException, BeanBagMismatchException, InvalidMonthException,
          IllegalNumberOfBeanBagsAddedException {
    BeanBagStore testStore = new Store();
    BeanBag testBag = new BeanBag("NAME", "Test", "TEST BAG", (short) 2020, (byte) 12);
    testStore.addBeanBags(10, "testing", "Sam", "RUN", (short) 2020, (byte) 12);
    testStore.addBeanBags(3, "test5", "SAM", "BATER", (short) 2020, (byte) 12);
    //testStore.setBeanBagPrice("RUN", 111);

    try {
      testStore.loadStoreContents("stocklist.txt");
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    try {
      testStore.saveStoreContents("stocklist.txt");
    } catch (IOException e) {
      System.out.println(e);
    }
    for (int i = 0; i < Store.stockList.size(); i++) {
      System.out.println(Store.stockList.get(i).toString());
    }
    // System.out.println(testBag.toString());
  }
}
