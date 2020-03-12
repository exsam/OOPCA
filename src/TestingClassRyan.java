import beanbags.BeanBagStore;
import beanbags.Store;

public class TestingClassRyan {
  public static void main(String[] args) {
    // System.out.println("Testing Start");
    BeanBagStore testing = new Store();
    try {
      testing.empty();

      testing.addBeanBags(10,"man","nam","00000000",(short)2010,(byte)12, "information1");
      testing.addBeanBags(10,"manu","nam","00000006",(short)2010,(byte)12);
      testing.addBeanBags(10,"man","nam","00007000",(short)2010,(byte)12);
      testing.addBeanBags(4,"man","nam","00000004",(short)2010,(byte)12);
      testing.setBeanBagPrice("00000000", 10);
      testing.setBeanBagPrice("00007000", 10);
      testing.setBeanBagPrice("00000004", 50);
      testing.setBeanBagPrice("00000006", 50);


      try {
        testing.saveStoreContents("stocklist.ser");
      } catch (Exception e) {
        e.printStackTrace();
      }
      testing.loadStoreContents("stocklist.ser");

      System.out.println("--------------------------------------------");
      System.out.println("(EXPECTED 34) Total Bean Bags in Stock: " + testing.beanBagsInStock());
      System.out.println("(EXPECTED 0) Reserved Bags in Stock: " + testing.reservedBeanBagsInStock());
      System.out.println("*Reserving 3 of Bean Bag with ID = 00000004");
      System.out.println("(EXPECTED 1) Reservation number = " + testing.reserveBeanBags(3, "00000004"));
      System.out.println("*Reserving 2 of Bean Bag with ID = 00000000");
      System.out.println("(EXPECTED 2)Reservation number = " + testing.reserveBeanBags(2, "00000000"));
      System.out.println("--------------------------------------------");
      System.out.println("(EXPECTED 5) Reserved Bags in Stock: " + testing.reservedBeanBagsInStock());
      System.out.println("(EXPECTED 34) Total Bean Bags in Stock: " + testing.beanBagsInStock());
      System.out.println("(EXPECTED 170) The total Price of reserved beanbags = " + testing.getTotalPriceOfReservedBeanBags());
      System.out.println("--------------------------------------------");
      System.out.println("UN-Reserving Bean Bag with Reservation No: 2)");
      testing.unreserveBeanBags(2);
      System.out.println("--------------------------------------------");
      System.out.println("(EXPECTED 3) Reserved Bags in Stock: " + testing.reservedBeanBagsInStock());
      System.out.println("(EXPECTED 150) The total Price of reserved beanbags = " + testing.getTotalPriceOfReservedBeanBags());
      System.out.println("--------------------------------------------");
      System.out.println("Selling 1 of beanBag ID 00000004");
      testing.sellBeanBags(1, "00000004");
      System.out.println("--------------------------------------------");
      try{
        System.out.println("Selling ANOTHER 1 of beanBag ID 00000004");
        System.out.println("(EXPECT InsufficientStockException)");
        testing.sellBeanBags(1, "00000004");
      } catch (Exception InsufficientStockException) {
        System.out.println("(*InsufficientStockException*)");
      }
      System.out.println("--------------------------------------------");
      System.out.println("(EXPECTED 3) Reserved Bags in Stock: " + testing.reservedBeanBagsInStock());
      System.out.println("(EXPECTED 150) The total Price of reserved beanbags = " + testing.getTotalPriceOfReservedBeanBags());
      System.out.println("--------------------------------------------");
      try{
        System.out.println("*Reserving 1 of Bean Bag with ID = 00000004");
        System.out.println("(EXPECT InsufficientStockException)");
        testing.reserveBeanBags(1, "00000004");
      } catch (Exception InsufficientStockException) {
        System.out.println("(*InsufficientStockException*)");
      }
      System.out.println("--------------------------------------------");
      System.out.println("(EXPECTED 3) Reserved Bags in Stock: " + testing.reservedBeanBagsInStock());
      System.out.println("(EXPECTED 150) The total Price of reserved beanbags = " + testing.getTotalPriceOfReservedBeanBags());
      System.out.println("(EXPECTED 33) Total Bean Bags in Stock: " + testing.beanBagsInStock());
      System.out.println("(EXPECTED 4) Total number of UNIQUE Bean Bags in stock " + testing.getNumberOfDifferentBeanBagsInStock());
      System.out.println("--------------------------------------------");
      System.out.println("Selling 1 of beanBag ID 00000006");
      testing.sellBeanBags(10, "00000006");
      System.out.println("--------------------------------------------");
      System.out.println("(EXPECTED 3) Reserved Bags in Stock: " + testing.reservedBeanBagsInStock());
      System.out.println("(EXPECTED 150) The total Price of reserved beanbags = " + testing.getTotalPriceOfReservedBeanBags());
      System.out.println("(EXPECTED 23) Total Bean Bags in Stock: " + testing.beanBagsInStock());
      System.out.println("(EXPECTED 11) Total Number of Sold Bean Bags: " + testing.getNumberOfSoldBeanBags());
      System.out.println("(EXPECTED 1) Total Number of Sold Bean Bags with ID 00000004: " + testing.getNumberOfSoldBeanBags("00000004"));
      System.out.println("(EXPECTED 10) Total Number of Sold Bean Bags with ID 00000006: " + testing.getNumberOfSoldBeanBags("00000006"));
      System.out.println("(EXPECTED 3) Total number of UNIQUE Bean Bags in stock " + testing.getNumberOfDifferentBeanBagsInStock());
      System.out.println(testing.getBeanBagDetails("00000000"));

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
