import beanbags.*;

import java.io.IOException;

public class TestingClassSam {
  public static void main(String[] args)
      throws IllegalIDException, BeanBagMismatchException, InvalidMonthException,
          IllegalNumberOfBeanBagsAddedException {
    BeanBagStore testStore = new Store();
    // testStore.addBeanBags(20,"test","banter","10",(short)2012,(byte)12);
    try {
      testStore.loadStoreContents("stocklist.txt");
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    // Check.matchingIDs(testBag, Store.stockList);
    Check.validID("00000000");
    testStore.addBeanBags(2,"Manufacturer1", "Ryan", "000000FF", (short)2020,(byte)7,"Testing the mismatch test.");
    testStore.addBeanBags(2,"Manufacturer1", "Ryan", "000000FF", (short)2020,(byte)7,"Testing the mismatch test.");

    System.out.println(Store.stockList.size());
    /*try {
      testStore.replace("ID5","ID4");
    } catch (BeanBagIDNotRecognisedException e) {
      System.out.println("NOPE");
    }*/
  }
}
