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

    //System.out.println("Bean Bags in stock: ");
    System.out.println(testing.beanBagsInStock());
    //System.out.println("Reserved Bean Bags in stock: ");
    System.out.println(testing.reservedBeanBagsInStock());
    if(!((BeanBag) ((Store) testing).stockList.get(1)).getReserved())
    {
      System.out.println(((BeanBag) ((Store) testing).stockList.get(2)).toString());
    }


    //System.out.println(testing.beanBagsInStock("test2"));


  }
}
