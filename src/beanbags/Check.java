package beanbags;

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
    if (id.length() == 8) {
      try {
        int deci = Long.valueOf(id, 16).intValue();
        if (deci < 0) {
          throw new IllegalIDException("ID must be a positive number.");
        }
      } catch (NumberFormatException e) {
        throw new IllegalIDException("ID is not a Hexidecimal number.");
      }
    } else {
      throw new IllegalIDException("ID must only be 8 characters long.");
    }
  }

  public static void matchingIDs(BeanBag bag, ObjectArrayList stockList)
      throws BeanBagMismatchException {
    for (int i = 0; i < stockList.size(); i++) {
      BeanBag tempBag = (BeanBag) stockList.get(i);
      if (bag.getID() == tempBag.getID()) {
          if (bag.getName() == tempBag.getName() & bag.getManufacturer() == tempBag.getManufacturer())
          {

          }
      }
    }
  }
}
