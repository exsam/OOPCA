import beanbags.BeanBagStore;
import beanbags.Store;

public class TestingClassRyan {
  public static void main(String[] args) {
    // System.out.println("Testing Start");
    BeanBagStore testing = new Store();
    try {
      testing.loadStoreContents("stocklist.ser");


      //System.out.println("Testing ID 00000002");
      //testing.getBeanBagDetails("00000002");

      //System.out.println("Total Bean Bags in Stock: " + testing.beanBagsInStock());
      //System.out.println("Reserved Bags in Stock: " + testing.reservedBeanBagsInStock());
      //System.out.println("*Reserving 3 of Bean Bag with ID = 00000004");
      //System.out.println("Reservation number = " + testing.reserveBeanBags(3, "00000004"));
      //System.out.println("--------------------------------------------");
      //System.out.println("Reserved Bags in Stock: " + testing.reservedBeanBagsInStock());


      //System.out.println("UN-Reserving Bean Bag with Reservation No: 3)");
      //testing.unreserveBeanBags(3);
      //System.out.print("\n Reserved Bags in Stock: ");
      //System.out.print(testing.reservedBeanBagsInStock());

      //System.out.println("The total Price of reserved beanbags = " + testing.getTotalPriceOfReservedBeanBags());
      //System.out.println("The info for bean bag ID 00000002 = " + testing.getBeanBagDetails("00000002"));

      //testing.sellBeanBags(1,"00000005");
      /*System.out.println("\nUNIQUE: " + testing.getNumberOfDifferentBeanBagsInStock());
      System.out.println(testing.beanBagsInStock());*/

      //testing.empty();
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
