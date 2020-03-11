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

  public static void matchingIDs(BeanBag bag, ObjectArrayList stockList)
      throws BeanBagMismatchException
  {
    //System.out.println("MatchingID's Was called");
    for (int i = 0; i < stockList.size(); i++) {
      BeanBag tempBag = (BeanBag) stockList.get(i);
      System.out.print("bag ID = ");
      System.out.print(bag.getID());
      System.out.print(" tempBag ID = ");
      System.out.print(tempBag.getID());
      System.out.print("\n");
      //
      if ((bag.getID()).equals(tempBag.getID()))
      {
        //System.out.println("They have matching ID!");
        if ( (bag.getName() == tempBag.getName())
        && (bag.getManufacturer() == tempBag.getManufacturer())
        && (bag.getInformation() == tempBag.getInformation()))
        {
          //System.out.println("ALL DATA MATCHES");
        }
        else
        {
          //System.out.println("ERROR - DATA DOESNT MATCH");
          throw new BeanBagMismatchException("ID does not match associated data!");
        }
      }
      else {
        //System.out.print("bag ID = ");
        //System.out.print(bag.getID());
        //System.out.print(" doesnt match tempBag ID = ");
        //System.out.print(tempBag.getID());
        //System.out.print("\n");
      }

/*      if (tempBag.getID() == ((BeanBag) stockList.get(i)).getID())
      {
        System.out.println("Bag have matching ID's");
      }
      else{
        System.out.println("Bags do not have matching ID's");
      }*/


      //System.out.print("Current bag = ");
      //System.out.print(bag);
      //System.out.print("\n Current tempbag = ");
      //System.out.print(tempBag);

/*      int counter = 0;
      if (bag.equals(tempBag)) {
        System.out.println("Beanbag Match");
      } else {
        System.out.println("Beanbag No Match");
        counter++;
      }
    }
    if (counter = stockList.size()) {
      throw new BeanBagMismatchException("Beanbag data mismatch detected");
    }*/

    //if (bag.getID() == tempBag.getID()) {
        //if (bag.getName() == tempBag.getName()
        //& bag.getManufacturer() == tempBag.getManufacturer() & bag.getYear() == tempBag.getYear() & bag.) {}*/
        //if (bag.equals(tempBag)) {
          //System.out.println("YEAH2");
    }
  }
}
