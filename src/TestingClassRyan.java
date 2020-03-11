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
      System.out.print("\n Reserving Bean Bag with ID4");
      System.out.print("\n Reservation number = ");
      System.out.print(testing.reserveBeanBags(1,"ID4"));
      System.out.print("\n Reserved Bags in Stock: ");
      System.out.print(testing.reservedBeanBagsInStock());
      System.out.println("UNIQUE: " + testing.getNumberOfDifferentBeanBagsInStock());
  } catch (Exception e) {
    e.printStackTrace();
  }
  try{
    testing.saveStoreContents("stocklist.txt");
  }
  catch (Exception e) {
    e.printStackTrace();
  }
  }
}