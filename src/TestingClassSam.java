import beanbags.*;

import java.io.IOException;

public class TestingClassSam {
  public static void main(String[] args)
      throws IllegalIDException, BeanBagMismatchException, InvalidMonthException,
          IllegalNumberOfBeanBagsAddedException {
    BeanBagStore testStore = new Store();
    //testStore.addBeanBags(20,"test","banter","10",(short)2012,(byte)12);
    try {
      testStore.loadStoreContents("stocklist.txt");
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    BeanBag testBag = new BeanBag("Ryan", "ID1", "Manufacturer1", (short)2020,(byte)7);
    Check.matchingIDs(testBag, Store.stockList);
    /*try {
      testStore.replace("ID5","ID4");
    } catch (BeanBagIDNotRecognisedException e) {
      System.out.println("NOPE");
    }*/
  }}
