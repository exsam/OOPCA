import beanbags.*;
import java.io.IOException;


public class TestingClassRyan {
  public static void main(String[] args) {
    //System.out.println("Testing Start");
    BeanBagStore testing = new Store();
    try {
      testing.loadStoreContents("stocklist.txt");
      //testing.reserveBeanBags(2, "test2");
    } catch (Exception e) {
      e.printStackTrace();
    }
    for (int i = 0; i < Store.stockList.size(); i++) {
      if(((BeanBag) Store.stockList.get(i)).getReserved()){
        System.out.println("TEST");
      }
    }
    //System.out.println("Bean Bags in stock: ");
    //System.out.println(testing.beanBagsInStock());
    //System.out.println("Reserved Bean Bags in stock: ");
    System.out.println(testing.reservedBeanBagsInStock());
    try {
      testing.saveStoreContents("testing.txt");
    } catch (IOException e) {
      e.printStackTrace();
    }


    //System.out.println(testing.beanBagsInStock("test2"));


  }
}
