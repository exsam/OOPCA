package beanbags;
import java.io.IOException;

/**
 * Implementor for the BeanBagStore Interface
 *
 * @author 690027367
 * @author 690025118
 * @version 0.1
 */
public class Store implements BeanBagStore{
    public void addBeanBags(int num, String manufacturer, String name,
                            String id, short year, byte month)

            // Call Constructor method for BeanBag
            // Add to object array list

            throws IllegalNumberOfBeanBagsAddedException, BeanBagMismatchException,
            IllegalIDException, InvalidMonthException { }

    public void addBeanBags(int num, String manufacturer, String name,
                            String id, short year, byte month, String information)

            // Same as above but different function signature (More data).

            throws IllegalNumberOfBeanBagsAddedException, BeanBagMismatchException,
            IllegalIDException, InvalidMonthException { }

    public void setBeanBagPrice(String id, int priceInPence)

            // Modify the price of a bean bag object

            throws InvalidPriceException, BeanBagIDNotRecognisedException, IllegalIDException { }

    public void sellBeanBags(int num, String id)

            // Method for searching via "ID"

            // Check bean bag in stock,
            // Check bean bag not reserved,
            // Check sufficient stock for quantity required,
            // Remove from ObjectArrayList

            throws BeanBagNotInStockException,
            InsufficientStockException, IllegalNumberOfBeanBagsSoldException,
            PriceNotSetException, BeanBagIDNotRecognisedException, IllegalIDException { }

    public int reserveBeanBags(int num, String id)

            // Only reserve IF in stock
            // Generate "Reservation Number"?
            // Only sold to customer with matching reservation number.

            throws BeanBagNotInStockException,
            InsufficientStockException, IllegalNumberOfBeanBagsReservedException,
            PriceNotSetException, BeanBagIDNotRecognisedException, IllegalIDException { return 0; }

    public void unreserveBeanBags(int reservationNumber)

            // Check which bean bag is related to that reservation number
            // Remove the reservation number as no longer reserved.

            throws ReservationNumberNotRecognisedException { }

    public void sellBeanBags(int reservationNumber)

            // Method for searching via "reservationNumber"

            // Check bean bag in stock,
            // Check reservation number matches,
            // Check sufficient stock for quantity required,
            // Remove from ObjectArrayList

            throws ReservationNumberNotRecognisedException { }

    public int beanBagsInStock() { return 0; }

            // No parameters
            // Return entire list of stock?

    public int reservedBeanBagsInStock() { return 0; }

            // No parameters
            // Return list of stock where there is a reservation number

    public int beanBagsInStock(String id)

            // Return stock quantity of BeanBag with passed ID

            throws BeanBagIDNotRecognisedException,
            IllegalIDException { return 0; }

    public void saveStoreContents(String filename)

            // Save as a text file with filename

            throws IOException { }


    public void loadStoreContents(String filename)

            // Load text file with matching filename

            throws IOException,
            ClassNotFoundException { }


    public int getNumberOfDifferentBeanBagsInStock() { return 0; }



    public int getNumberOfSoldBeanBags() { return 0; }

    public int getNumberOfSoldBeanBags(String id) throws
            BeanBagIDNotRecognisedException, IllegalIDException { return 0; }

    public int getTotalPriceOfSoldBeanBags() { return 0; }

    public int getTotalPriceOfSoldBeanBags(String id) throws
            BeanBagIDNotRecognisedException, IllegalIDException { return 0; }

    public int getTotalPriceOfReservedBeanBags() { return 0; }

    public String getBeanBagDetails(String id) throws
            BeanBagIDNotRecognisedException, IllegalIDException { return ""; }

    public void empty() { }

    public void resetSaleAndCostTracking() { }

    public void replace(String oldId, String replacementId)
            throws BeanBagIDNotRecognisedException, IllegalIDException { }
}
