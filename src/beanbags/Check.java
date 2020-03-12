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
    // this means ID has a range 00000000-7FFFFFFF
    if (id.length() == 8) {
      try {
        int deci = Long.valueOf(id, 16).intValue();
        if (deci < 0) {
          throw new IllegalIDException(
              "ID must be a positive number (within range 00000000-7FFFFFFF)");
        }
      } catch (NumberFormatException e) {
        throw new IllegalIDException("ID is not a Hexidecimal number.");
      }
    } else {
      throw new IllegalIDException("ID must only be 8 characters long.");
    }
  }

  public static void matchingIDs(BeanBag bag)
      throws BeanBagMismatchException {
    // System.out.println("MatchingID's Was called");
    for (int i = 0; i < stockList.size(); i++) {
      BeanBag tempBag = (BeanBag) stockList.get(i);
      if ((bag.getID()).equals(tempBag.getID())) {
        if (bag.getName().equals(tempBag.getName())
            & bag.getName().equals(tempBag.getManufacturer())
            & bag.getInformation().equals(tempBag.getInformation())) {
          throw new BeanBagMismatchException("ID does not match existing");
        }
      }
    }
  }

  public static void fulfillRequest(int num, String id) throws BeanBagIDNotRecognisedException,
          BeanBagNotInStockException,InsufficientStockException {
    int currentCounter = 0;
    int oldCounter = 0;
    for (int i = 0; i < stockList.size(); i++) {
      BeanBag bag = (BeanBag) stockList.get(i);
      if ((bag.getID()).equals(id) && !bag.getReserved() && !bag.isSold()) {
        if (bag.isSold()) {
          oldCounter++;
        } else {
          currentCounter++;
        }
        System.out.print("oldCounter: " + oldCounter);
        System.out.print("currentCounter: " + currentCounter);
      }
    }

    if (currentCounter == 0 & oldCounter == 0) {
      throw new BeanBagIDNotRecognisedException("No BeanBags with this ID exist on our system.");
    }
    if (currentCounter == 0 & oldCounter >= 1) {
      throw new BeanBagNotInStockException(id + " is not in stock.");
    }
    if (currentCounter < num) {
      throw new InsufficientStockException("We don't have enough BeanBags to fulfil this order.");
    }
  }
}
