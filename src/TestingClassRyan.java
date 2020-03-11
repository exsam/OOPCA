import beanbags.BeanBagStore;
import beanbags.Store;

public class TestingClassRyan {
  public static void main(String[] args) {
    // System.out.println("Testing Start");
    BeanBagStore testing = new Store();
    try {
      testing.loadStoreContents("stocklist.txt");
      System.out.print("Total Bean Bags in Stock: ");
      System.out.print(testing.beanBagsInStock());
      System.out.print("\n Reserved Bags in Stock: ");

      System.out.print(testing.reservedBeanBagsInStock());
      System.out.print("\n Reserving Bean Bag with ID = 00000004");
      System.out.print("\n Reservation number = ");
      System.out.print(testing.reserveBeanBags(1, "00000004"));
      System.out.print("\n Reserved Bags in Stock: ");
      System.out.print(testing.reservedBeanBagsInStock());


      System.out.println("\nUNIQUE: " + testing.getNumberOfDifferentBeanBagsInStock());

      System.out.println(testing.beanBagsInStock());

      System.out.println(Store.stockList.size());
    } catch (Exception e) {
      e.printStackTrace();
    }

    /*try {
      testing.saveStoreContents("stocklist.txt");
    } catch (Exception e) {
      e.printStackTrace();
    }*/
  }
}
