import beanbags.*;
import java.io.IOException;


public class TestingClassRyan {
  public static void main(String[] args) {
    //System.out.println("Testing Start");
    BeanBagStore testing = new Store();
    try {
      testing.loadStoreContents("stocklist.txt");
      System.out.print("Total Bean Bags in Stock: ");
      System.out.print(testing.beanBagsInStock());
      System.out.print("\n Reserved Bags in Stock: ");
      System.out.print(testing.reservedBeanBagsInStock());
      System.out.print("\n Reserving Bean Bag with ID3");
      testing.reserveBeanBags(1,"ID3");
      system.out.print("RUNNNNNNN");
      System.out.print("\n Reserved Bags in Stock: ");
      System.out.print(testing.reservedBeanBagsInStock());
  } catch (Exception e) {
    e.printStackTrace();
  }

    //System.out.println(testing.beanBagsInStock("test2"));

  }
}
