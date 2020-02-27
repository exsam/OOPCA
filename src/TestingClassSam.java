import beanbags.*;

import java.io.IOException;

public class TestingClassSam {
  public static void main(String[] args)
      throws IllegalIDException, BeanBagMismatchException, InvalidMonthException,
          IllegalNumberOfBeanBagsAddedException {
    Store testStore = new Store();
    BeanBag testBag = new BeanBag("NAME", "Test", "TEST BAG", (short) 2020, (byte) 12);
    testStore.addBeanBags(10, "testing", "Sam", "RUN", (short) 2020, (byte) 12);
    testStore.addBeanBags(0, "test2", "SAM", "BATER", (short) 2020, (byte) 12);
    // testStore.loadStoreContents("stocklist.txt");
    try {
      testStore.saveStoreContents("stocklist.txt");
    } catch (IOException e) {
      System.out.println(e);
    }
    for (int i = 0; i < testStore.stockList.size(); i++) {
      System.out.print(testStore.stockList.get(i).toString());
    }
    // System.out.println(testBag.toString());
  }
}
