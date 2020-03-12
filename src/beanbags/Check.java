package beanbags;

import static beanbags.Store.stockList;

/**
 * Class for static Exception Handling methods
 *
 * @author 690027367 & 690025118
 * @version 0.1
 */
public class Check {

  /**
   * Method for Exception Handling to ensure ID is a positive 8 digit Hexadecimal number
   *
   * @param id
   * @throws IllegalIDException
   */
  public static void validID(String id) throws IllegalIDException {
    // If the passed ID parameter has the correct length of "8".
    if (id.length() == 8) {
      try {
        // Converts a signed hexadecimal number to an integer named "deci".
        int deci = Long.valueOf(id, 16).intValue();
        // If the "deci" integer is less than 0.
        if (deci < 0) {
          // Throw "IllegalIDException" as the ID is invalid (Wrong Range).
          throw new IllegalIDException(
              "ID must be a positive number (within range 00000000-7FFFFFFF)");
        }
      } catch (NumberFormatException e) {
        // Throw "IllegalIDException" as the ID is invalid (Wrong format).
        throw new IllegalIDException("ID is not a Hexidecimal number.");
      }
    } else {
      // Throw "IllegalIDException" as the ID is invalid (Wrong Length).
      throw new IllegalIDException("ID must only be 8 characters long.");
    }
  }

  /**
   * Method checks if the ID of a passed bag matches the rest of the data for that ID.
   *
   * @param bag
   * @throws BeanBagMismatchException
   */
  public static void matchingIDs(BeanBag bag) throws BeanBagMismatchException {

    System.out.println("Evaluating ID: " + bag);

    // Loop through every object in the "stockList" object array list.
    for (int i = 0; i < stockList.size(); i++) {

      System.out.println("Checking against: " + (BeanBag) stockList.get(i));

      // Create temporary "tempBag" object from current "stockList" location.
      BeanBag tempBag = (BeanBag) stockList.get(i);
      // Check that if the ID of the "tempBag" matches the ID of the bag in the "stockList".
      if ((bag.getID()).equals(tempBag.getID())) {

        System.out.println("The ID's Match");

        // If the Name, Manufacturer and Information across both bags matches.
        if (bag.getName().equals(tempBag.getName())
            && bag.getManufacturer().equals(tempBag.getManufacturer())
            && bag.getInformation().equals(tempBag.getInformation())) {
          //System.out.println("Data Match");
        }
        else {
          //System.out.println("Data Mismatch!");
          // Throw "BeanBagMismatchException" as the ID doe not match the rest of the data.
          throw new BeanBagMismatchException("ID does not match existing");
        }
      }
    }
  }

  /**
   * Ensures large enough quantity of available stock to fulfill request
   *
   * @param num
   * @param id
   * @throws BeanBagIDNotRecognisedException
   * @throws BeanBagNotInStockException
   * @throws InsufficientStockException
   */
  public static void fulfillRequest(int num, String id)
      throws BeanBagIDNotRecognisedException, BeanBagNotInStockException,
          InsufficientStockException {
    // Define integer "currentCounter" and set it's value to 0.
    int currentCounter = 0;
    // Define integer "oldCounter" and set it's value to 0.
    int oldCounter = 0;
    // Loop through every object in the "stockList" object array list.
    for (int i = 0; i < stockList.size(); i++) {
      // Define a temporary bag called "bag" from the array location.
      BeanBag bag = (BeanBag) stockList.get(i);
      // If the Bag ID matches the passed parameter ID AND the bag is NOT reserved and NOT sold.
      if ((bag.getID()).equals(id) && !bag.isSold()) {
        // If the bag is sold.
        if (bag.isSold() | bag.getReserved()) {
          // Increment integer "oldCounter" by 1.
          oldCounter++;
          // If te bag is not sold.
        } else {
          // Increment integer "currentCounter" by 1.
          currentCounter++;
        }
      }
    }

    // If the "currentCounter" and "oldCounter" both = 0.
    if (currentCounter == 0 & oldCounter == 0) {
      // Throw "BeanBagIDNotRecognisedException", as the ID is not found.
      throw new BeanBagIDNotRecognisedException("No BeanBags with this ID exist on our system.");
    }
    // If the "currentCounter" = 0 and the "oldCounter" is greater than or equal to 1.
    if (currentCounter == 0 & oldCounter >= 1) {
      // Throw "BeanBagIDNotInStockException", as the beanBag is not in stock.
      throw new BeanBagNotInStockException(id + " is not in stock.");
    }
    // If the "currentCounter" is smaller than "num".
    if (currentCounter < num) {
      // Throw "InsufficientStockException", as there are not enough beanBags in stock for the
      // order.
      throw new InsufficientStockException("We don't have enough BeanBags to fulfil this request.");
    }
  }

  /**
   * Method checks that there is a bag reserved with given ID
   *
   * @param resNum
   * @throws ReservationNumberNotRecognisedException
   */
  public static void reservedAvailable(int resNum) throws ReservationNumberNotRecognisedException {
    // Define integer "currentCounter" and set it's value to 0.
    int counter = 0;
    // Loop through every object in the "stockList" object array list.
    for (int i = 0; i < stockList.size(); i++) {
      // If the beanBag's reservation number in the "stockList" matches the passed "resNum".
      if (((BeanBag) stockList.get(i)).getReservationNumber() == resNum) {
        // Increment the variable "counter" by 1.
        counter++;
        // Break from the loop.
        break;
      }
    }
    // If the "counter" integer variable is less than 1.
    if (counter < 1) {
      // Throw "ReservationNumberNotRecognisedException", as no matches were found whilst looping.
      throw new ReservationNumberNotRecognisedException(
          "No BeanBags with this Reservation Number in stock.");
    }
  }

  /**
   * Check if Bag with id has ever been in Stock
   *
   * @param id
   * @throws BeanBagIDNotRecognisedException if the ID is legal, but does not match any bag in (or *
   *     * previously in) stock
   */
  public static void idEverExists(String id) throws BeanBagIDNotRecognisedException {
    int n = 0;
    for (int i = 0; i < stockList.size(); i++) {
      if (((BeanBag) stockList.get(i)).getID().equals(id)) {
        n++;
      }
    }
    if (n < 1) {
      throw new BeanBagIDNotRecognisedException();
    }
  }
}
