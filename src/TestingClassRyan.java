import beanbags.BeanBagStore;
import beanbags.Store;

public class TestingClassRyan {
  public static void main(String[] args) {
    // System.out.println("Testing Start");
    BeanBagStore testing = new Store();
    try {
      testing.empty();

      testing.addBeanBags(10,"man","nam","00000000",(short)2010,(byte)12);
      testing.addBeanBags(10,"manu","nam","00000006",(short)2010,(byte)12);
      testing.addBeanBags(10,"man","nam","00007000",(short)2010,(byte)12);
      testing.addBeanBags(4,"man","nam","00000004",(short)2010,(byte)12);
      testing.setBeanBagPrice("00000004", 50);
      testing.setBeanBagPrice("00000000", 10);

      try {
        testing.saveStoreContents("stocklist.ser");
      } catch (Exception e) {
        e.printStackTrace();
      }
      testing.loadStoreContents("stocklist.ser");

      System.out.println("--------------------------------------------");
      System.out.println("Total Bean Bags in Stock: " + testing.beanBagsInStock());
      System.out.println("Reserved Bags in Stock: " + testing.reservedBeanBagsInStock());
      System.out.println("*Reserving 3 of Bean Bag with ID = 00000004");
      System.out.println("Reservation number = " + testing.reserveBeanBags(3, "00000004"));
      System.out.println("*Reserving 2 of Bean Bag with ID = 00000000");
      System.out.println("Reservation number = " + testing.reserveBeanBags(2, "00000000"));
      System.out.println("--------------------------------------------");
      System.out.println("Reserved Bags in Stock: " + testing.reservedBeanBagsInStock());
      System.out.println("The total Price of reserved beanbags = " + testing.getTotalPriceOfReservedBeanBags());
      System.out.println("--------------------------------------------");
      System.out.println("UN-Reserving Bean Bag with Reservation No: 1)");
      testing.unreserveBeanBags(1);
      System.out.println("--------------------------------------------");
      System.out.println("Reserved Bags in Stock: " + testing.reservedBeanBagsInStock());
      System.out.println("The total Price of reserved beanbags = " + testing.getTotalPriceOfReservedBeanBags());
      System.out.println("The number of Unique Bean Bags in stock = " + testing.getNumberOfDifferentBeanBagsInStock());
      System.out.println("--------------------------------------------");
      System.out.println("Emptying the store");
      System.out.println("--------------------------------------------");
      testing.empty();
      System.out.println("Total Bean Bags in Stock: " + testing.beanBagsInStock());
      System.out.println("Reserved Bags in Stock: " + testing.reservedBeanBagsInStock());
      System.out.println("The number of Unique Bean Bags in stock = " + testing.getNumberOfDifferentBeanBagsInStock());
      System.out.println("The total Price of reserved beanbags = " + testing.getTotalPriceOfReservedBeanBags());
      //testing.addBeanBags(10,"man","nam","00000000",(short)2010,(byte)12);

    } catch (Exception e) {
      e.printStackTrace();
    }
    try {
      testing.saveStoreContents("stocklist.ser");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
