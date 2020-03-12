package beanbags;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Implementor for the BeanBagStore Interface
 *
 * @author 690027367 & 690025118
 * @version 0.1
 */
public class Store implements BeanBagStore {

  private static ObjectArrayList stockList = new ObjectArrayList();

  // Define private integer variable "nextReservationNum" and set it's value to 0.
  private int nextReservationNum = 0;

  public void addBeanBags(
      int num, String manufacturer, String name, String id, short year, byte month)
      throws IllegalNumberOfBeanBagsAddedException, BeanBagMismatchException, IllegalIDException,
          InvalidMonthException {
    // Run the "validID" function in the "Check" class to see if the format is correct.
    Check.validID(id);
    BeanBag testBag = new BeanBag(name, id, manufacturer, year, month);
    Check.matchingIDs(testBag,stockList);
    // If the "month" byte variable is greater than 12 or less than or equal to 0.
    if (month <= 0 || month > 12) {
      // Throw exception "InvalidMonthException", as the month is not correctly formatted.
      throw new InvalidMonthException(
          month + " is an invalid month. Please enter a valid month 1-12");
    }
    // Ensures number of BeanBags passed to function ("num") is valid (Greater than or equal to 1).
    if (num >= 1) {
      // Repeat the following code "num" times.
      for (int i = 0; i < num; i++) {
        // Define a new "BeanBag" object with the following parameters.
        BeanBag tempBag = new BeanBag(name, id, manufacturer, year, month);
        // Add the new beanBag "tempBag" object to the "stockList".
        stockList.add(tempBag);
      }
      // If number of BeanBags passed to function ("num") is NOT greater than or equal to 1.
    } else {
      // Throw exception "IllegalNumberOfBeanBagsAddedException", as number passed to the function
      // is wrong.
      throw new IllegalNumberOfBeanBagsAddedException(
          "Number of bags must be" + " must be a whole integer and greater then 0.");
    }
  }

  public void addBeanBags(
      int num,
      String manufacturer,
      String name,
      String id,
      short year,
      byte month,
      String information)
      throws IllegalNumberOfBeanBagsAddedException, BeanBagMismatchException, IllegalIDException,
          InvalidMonthException {
    // Run the "validID" function in the "Check" class to see if the format is correct.
    Check.validID(id);
    BeanBag testBag = new BeanBag(name,id,manufacturer,information,year,month);
    Check.matchingIDs(testBag,stockList);
    // If the "month" byte variable is greater than 12 or less than or equal to 0.
    if (month <= 0 || month > 12) {
      // Throw exception "InvalidMonthException", as the month is not correctly formatted.
      throw new InvalidMonthException(
          month + " is an invalid month. Please enter a valid month 1-12");
    }
    // Ensures number of BeanBags passed to function ("num") is valid (Greater than or equal to 1).
    if (num >= 1) {
      // Repeat the following code "num" times.
      for (int i = 1; i <= num; i++) {
        // Define a new "BeanBag" object with the following parameters.
        BeanBag tempBag = new BeanBag(name, id, manufacturer, information, year, month);
        // Add the new beanBag "tempBag" object to the "stockList".
        stockList.add(tempBag);
      }
      // If number of BeanBags passed to function ("num") is NOT greater than or equal to 1.
    } else {
      // Throw exception "IllegalNumberOfBeanBagsAddedException", as number passed to the function
      // is wrong.
      throw new IllegalNumberOfBeanBagsAddedException(
          "Number of bags must be a whole integer and greater then 0.");
    }
  }

  /**
   * Gets the next available reservation number.
   *
   * @return the next available reservation number
   */
  private int getNextResNum() {
    // Loop through every object in the "stockList" object array list.
    for (int i = 0; i < stockList.size(); i++) {
      // If the "reservationNumber" of the beanBag in the "stockList"
      // is greater than the "nextReservationNum" global integer.
      if (((BeanBag) stockList.get(i)).getReservationNumber() > nextReservationNum) {
        // Set the global integer "nextReservationNum" to the value of the current
        // Reservation number of the beanBag in the "stockList".
        nextReservationNum = ((BeanBag) stockList.get(i)).getReservationNumber();
      }
    }
    // Increment the "nextReservationNum" integer by 1.
    nextReservationNum = nextReservationNum + 1;
    // Return the value of the "nextReservationNum" integer variable.
    return nextReservationNum;
  }

  public void setBeanBagPrice(String id, int priceInPence)
      throws InvalidPriceException, BeanBagIDNotRecognisedException, IllegalIDException {
    // Run the "validID" function in the "Check" class to see if the format is correct.
    Check.validID(id);
    // If the "priceinPence" integer is less than 1.
    if (priceInPence < 1) {
      // Throw exception "InvalidPriceException", as the price is not correctly formatted.
      throw new InvalidPriceException("Price must be 1 pence or higher");
    }
    // Define integer "counter" and set the value to 0.
    int counter = 0;
    // Loop through every object in the "stockList" object array list.
    for (int i = 0; i < stockList.size(); i++) {
      // If the ID of the beanBag in the stockList matches the passed parameter ID
      // AND the beanBag is NOT already reserved.
      if ((((BeanBag) stockList.get(i)).getID()).equals(id)
          && !((BeanBag) stockList.get(i)).getReserved()) {
        // Set the "price" integer in the "stockList" to the "priceInPence" integer parameter.
        ((BeanBag) stockList.get(i)).setPrice(priceInPence);
        // Increment the "counter" integer by 1.
        counter++;
      }
    }
    // If the "counter" integer is less than or equal to 0.
    if (counter <= 0) {
      // Throw exception "BeanBagIDNotRecognisedException", as the beanbag ID doesn't exist.
      throw new BeanBagIDNotRecognisedException("No BeanBags with ID: " + id + " were found.");
    }
  }

  public void sellBeanBags(int num, String id)
      throws BeanBagNotInStockException, InsufficientStockException,
          IllegalNumberOfBeanBagsSoldException, PriceNotSetException,
          BeanBagIDNotRecognisedException, IllegalIDException {
    if (num < 1) {
      throw new IllegalNumberOfBeanBagsSoldException(
          "Please enter a quantity to be sold greater or equal to 1");
    }
    Check.validID(id);
    Check.fulfillRequest(num, id,stockList);

    int fulfilledSold = 0;
    // Loop the following code "num" times.
    for (int j = 0; j < stockList.size(); j++) {
      if (fulfilledSold < num) {
        // If the current beanBag in the "stockList" reserved state boolean is NOT true and bag is
        // not already sold.
        if (!((BeanBag) stockList.get(j)).getReserved() && !((BeanBag) stockList.get(j)).isSold()) {
          // If the ID in the stockList matches the passed parameter ID
          if (((BeanBag) stockList.get(j)).getID().equals(id)) {
            if (((BeanBag) stockList.get(j)).getPrice() < 1) {
              throw new PriceNotSetException("Please Set A Price For BeanBag " + id);
            }
            // Set the beanBag reserved state to "true" in the "stockList".
            ((BeanBag) stockList.get(j)).setSold(true);
            fulfilledSold = fulfilledSold + 1;
          }
        }
      } else {
        break;
      }
    }
  }

  public int reserveBeanBags(int num, String id)
      throws BeanBagNotInStockException, InsufficientStockException,
          IllegalNumberOfBeanBagsReservedException, PriceNotSetException,
          BeanBagIDNotRecognisedException, IllegalIDException {
    // If "num" to reserve is less than 1, (Exception Handling).
    if (num < 1) {
      // Throw "IllegalNumberOfBeanBagsReservedException", reserved quantity should always be
      // greater than 1.
      throw new IllegalNumberOfBeanBagsReservedException(
          "Please enter a quantity to be reserved greater or equal to " + "1");
    }
    // Run the "validID" function in the "Check" class to see if the format is correct.
    Check.validID(id);
    // Run the "fulfillRequest" function in the "Check" class to see if there is enough stock.
    Check.fulfillRequest(num, id,stockList);
    // Define the int variable "reservationNum" as the result from the function "GetNextRestNum()".
    int reservationNum = getNextResNum();
    // Define the int variable "fulFilledReserved" and set the value to 0.
    int fulfilledReserved = 0;
    // Loop the following code "num" times.
    for (int j = 0; j < stockList.size(); j++) {
      // If the current beanBag in the "stockList" reserved state boolean is NOT true.
      if (fulfilledReserved < num) {
        if (!((BeanBag) stockList.get(j)).getReserved() & !((BeanBag) stockList.get(j)).isSold()) {
          // If the ID in the stockList matches the passed parameter ID
          if (((BeanBag) stockList.get(j)).getID().equals(id)) {
            if (((BeanBag) stockList.get(j)).getPrice() < 1) {
              throw new PriceNotSetException("Please Set A Price For BeanBag " + id);
            }
            // System.out.println("Matching ID and NOT Reserved");
            // Set the beanBag reserved state to "true" in the "stockList".
            ((BeanBag) stockList.get(j)).setReserved(true);
            // Set the beanBag reservation number in the "stockList" to the value of the integer
            // "ReservationNum".
            ((BeanBag) stockList.get(j)).setReservationNumber(reservationNum);
            fulfilledReserved = fulfilledReserved + 1;
          }
        }
      } else {
        break;
      }
    }
    // Return the value of the "ReservationNum" string variable.
    return reservationNum;
  }

  public void unreserveBeanBags(int reservationNumber)
      throws ReservationNumberNotRecognisedException {
    Check.reservedAvailable(reservationNumber,stockList);
    // Loop through every object in the "stockList" object array list.
    for (int i = 0; i < stockList.size(); i++) {
      // If the current beanBag's reservation number in the "stockList" matches the one passed as a
      // parameter.
      if (((BeanBag) stockList.get(i)).getReservationNumber() == reservationNumber) {
        // Set the beanBag reserved state to "false" in the "stockList".
        ((BeanBag) stockList.get(i)).setReserved(false);
        // Set the beanBag reservation number to "0" in the "stockList".
        ((BeanBag) stockList.get(i)).setReservationNumber(0);
      }
    }
  }

  public void sellBeanBags(int reservationNumber) throws ReservationNumberNotRecognisedException {

    // Loop the following code "num" times.
    for (int j = 0; j < stockList.size(); j++) {
      // If the current beanBag in the "stockList" reserved state boolean is NOT true and bag is
      // not already sold.
      System.out.println(((BeanBag) stockList.get(j)).getReservationNumber());
      if (((BeanBag) stockList.get(j)).getReservationNumber() == reservationNumber) {
        // Set the beanBag reserved state to "true" in the "stockList".
        System.out.println("TEST");
        ((BeanBag) stockList.get(j)).setSold(true);
      }
    }
    unreserveBeanBags(reservationNumber);
  }

  public int beanBagsInStock() {
    // Define integer "counter" and set the value to 0.
    int counter = 0;
    // Loop through every object in the "stockList" object array list.
    for (int i = 0; i < stockList.size(); i++) {
      // If the current beanBag in the "stockList" sold state boolean is true.
      if (!((BeanBag) stockList.get(i)).isSold()) {
        // Increment the "counter" integer by 1.
        counter++;
      }
    }
    // Return the value of the "counter" integer.
    return counter;
  }

  public int reservedBeanBagsInStock() {
    // Define integer "ReservedStock" and set the value to 0.
    int ReservedStock = 0;
    // Loop through every object in the "stockList" object array list.
    for (int i = 0; i < stockList.size(); i++) {
      // If the current beanBag in the "stockList"ches reserved state boolean is true.
      if (((BeanBag) stockList.get(i)).getReserved()) {
        // Increment the "ReservedStock" integer by 1.
        ReservedStock = ReservedStock + 1;
      }
    }
    // Return the value of the "ReservedStock" integer.
    return ReservedStock;
  }

  public int beanBagsInStock(String id) throws BeanBagIDNotRecognisedException, IllegalIDException {
    Check.validID(id);
    Check.idEverExists(id,stockList);
    // Define integer "bagStock" and set the value to 0.
    int bagStock = 0;
    // Loop through every object in the "stockList" object array list.
    for (int i = 0; i < stockList.size(); i++) {
      // If the ID in the stockList matches the passed parameter ID.
      if (((BeanBag) stockList.get(i)).getID().equals(id)
          & !((BeanBag) stockList.get(i)).isSold()) {
        // Increment the "BagStock" integer by 1.
        bagStock = bagStock + 1;
      }
    }
    // Return the value of the "bagStock" integer.
    return bagStock;
  }

  public void saveStoreContents(String filename) throws IOException {
    try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))){
      out.writeObject(stockList);
    }
  }

  public void loadStoreContents(String filename) throws IOException, ClassNotFoundException {
    try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))){
      stockList = (ObjectArrayList) in.readObject();
    }
  }

  public int getNumberOfDifferentBeanBagsInStock() {
    // Define integer "counter" and set the value to 0.
    int counter = 0;
    // Define integer "tracker" and set the value to 0.
    int tracker;
    // Define new string array "idArray" with same length as "stockList".
    String[] idArray = new String[stockList.size()];
    // Loop through every object in the "stockList" object array list.
    for (int i = 0; i < stockList.size(); i++) {
      // Set string "ID" to the id in the current "stockList" location
      String ID = ((BeanBag) stockList.get(i)).getID();
      // Set the "tracker" integer to 0.
      tracker = 0;
      // Again, Loop through every object in the "stockList" object array list.
      for (int j = 0; j < stockList.size(); j++) {
        // If the string "ID" matches the current position in the "idArray".
        if (ID.equals(idArray[j])) {
          // Increment the "tracker" integer by 1.
          tracker += 1;
        }
      }
      // If the "tracker" integer equals 0.
      if (tracker == 0) {
        // Loop through every object in the "stockList" object array list.
          // Set the element at position "counter" in "idArray" to the value of "ID".
          idArray[counter] = ID;
          // Increment the "counter" integer by 1.
          counter++;
      }
    }
    // Return the value of the "counter" integer.
    return counter;
  }

  public int getNumberOfSoldBeanBags() {
    // Define integer "counter" and set the value to 0.
    int counter = 0;
    // Loop through every object in the "stockList" object array list.
    for (int i = 0; i < stockList.size(); i++) {
      // If the ID in the stockList matches the passed parameter ID
      if (((BeanBag) stockList.get(i)).isSold()) {
        // Increment the "counter" integer by 1.
        counter++;
      }
    }
    // Return the value of the "counter" integer.
    return counter;
  }

  public int getNumberOfSoldBeanBags(String id)
      throws BeanBagIDNotRecognisedException, IllegalIDException {
    // Run the "validID" function in the "Check" class to see if the format is correct.
    Check.validID(id);
    // Define integer "counter" and set the value to 0.
    int counter = 0;
    // Loop through every object in the "stockList" object array list.
    for (int i = 0; i < stockList.size(); i++) {
      // If the ID in the stockList matches the passed parameter ID
      // AND the BeanBag boolean state "isSold()" is true.
      if ((((BeanBag) stockList.get(i)).getID()).equals(id)
          & ((BeanBag) stockList.get(i)).isSold()) {
        // Increment the "counter" integer by 1.
        counter++;
      }
    }
    // If the "counter" integer is 0.
    if (counter == 0) {
      // Throw exception BeanBagIDNotRecognisedException, as no beanBags with that ID have been
      // sold.
      throw new BeanBagIDNotRecognisedException("No BeanBags with this ID have been sold.");
    }
    // Return the value of the "counter" integer.
    return counter;
  }

  public int getTotalPriceOfSoldBeanBags() {
    // Define Int "salesTotal" to store total price of sold bean bags.
    int salesTotal = 0;
    // Loop through every object in the "stockList" object array list.
    for (int i = 0; i < stockList.size(); i++) {
      // If the current "stockList" beanBag boolean state "isSold()" is true.
      if (((BeanBag) stockList.get(i)).isSold()) {
        // Increment the "salesTotal" variable by the beanBag price.
        salesTotal += ((BeanBag) stockList.get(i)).getPrice();
      }
    }
    // Return the value of the integer "salesTotal".
    return salesTotal;
  }

  public int getTotalPriceOfSoldBeanBags(String id)
      throws BeanBagIDNotRecognisedException, IllegalIDException {

    // Define Int "salesTotal" to store total price of sold bean bags.
    int salesTotal = 0;
    // Run the "validID" function in the "Check" class to see if the format is correct.
    Check.validID(id);
    // Loop through every object in the "stockList" object array list.
    for (int i = 0; i < stockList.size(); i++) {
      // If the ID in the stockList matches the passed parameter ID
      // AND the BeanBag boolean state "isSold()" is true.
      if ((((BeanBag) stockList.get(i)).getID()).equals(id)
          & ((BeanBag) stockList.get(i)).isSold()) {
        // Increment the "salesTotal" variable by the beanBag price.
        salesTotal += ((BeanBag) stockList.get(i)).getPrice();
      }
    }
    // If the "salesTotal" integer is 0.
    if (salesTotal == 0) {
      // Throw exception BeanBagIDNotRecognisedException, as no beanBags with that ID have been
      // sold.
      throw new BeanBagIDNotRecognisedException("No BeanBags with this ID have been sold.");
    }
    // Return the value of the integer "salesTotal".
    return salesTotal;
  }

  public int getTotalPriceOfReservedBeanBags() {
    // Define Int "TotalReservedPrice" to store total price of reserved bean bags.
    int TotalReservedPrice = 0;
    // Loop through every object in the "stockList" object array list.
    for (int i = 0; i < stockList.size(); i++) {
      // Define int "CurrentPrice" and set to value in the "stockList" object array list.
      int CurrentPrice = ((BeanBag) stockList.get(i)).getPrice();
      // If the beanbag in "stockList" is reserved.
      if (((BeanBag) stockList.get(i)).getReserved()) {
        // Increment the value of "TotalReservedPrice" by int "CurrentPrice".
        TotalReservedPrice = TotalReservedPrice + CurrentPrice;
      }
    }
    // Once all value's examined, return the value of "TotalReservedPrice" int.
    return TotalReservedPrice;
  }

  public String getBeanBagDetails(String id)
      throws BeanBagIDNotRecognisedException, IllegalIDException {
    // Run the "validID" function in the "Check" class to see if the format is correct.
    Check.validID(id);
    // Define integer "n" and set value to 0.
    int n = 0;
    // Define string "Info" with null value.
    String info = "";
    // Loop through every object in the "stockList" object array list.
    for (int i = 0; i < stockList.size(); i++) {
      // If the ID in the stockList matches the passed parameter ID.
      if (((BeanBag) stockList.get(i)).getID().equals(id)) {
        // Set the "Info" string to the value stored in the "stockList".
        info = ((BeanBag) stockList.get(i)).getInformation();
        // Increment variable integer "n" by 1.
        n++;
        // Condition met, so break from the loop.
        break;
      }
    }
    if (n < 1) {
      throw new BeanBagIDNotRecognisedException("No BeanBags with this ID have been sold.");
    }
    // Return the string "Info".
    return info;
  }

  public void empty() {
    // Loop through every object in the "stockList" object array list.
    int StartSize = stockList.size();
    // While array is not empty.
    while (stockList.size() > 0) {
      // For every item in the "stockList" array.
      for (int i = 0; i < StartSize; i++) {
        // Remove the beanBag object at the current position in the "stockList".
        stockList.remove(i);
        // Set the global int "nextReservationNumber" to 0.
        nextReservationNum = 0;
      }
    }
  }

  public void resetSaleAndCostTracking() {
    // Loop through every object in the "stockList" object array list.
    for (int i = 0; i < stockList.size(); i++) {
      // If the beanBag object at the current position is "isSold" bool is true.
      if (((BeanBag) stockList.get(i)).isSold()) {
        // Remove beanBag object at that position from the "stockList".
        stockList.remove(i);
      }
    }
  }

  public void replace(String oldID, String replacementID)
      throws BeanBagIDNotRecognisedException, IllegalIDException {
    // Define int "Counter" and set value to 0.
    int counter = 0;
    // Loop through every object in the "stockList" object array list.
    for (int i = 0; i < stockList.size(); i++) {
      // If the ID in the stockList matches the replacement ID.
      if ((((BeanBag) stockList.get(i)).getID()).equals(replacementID)) {
        // Throw IllegalIdException as the new ID matches the old ID.
        throw new IllegalIDException("This ID already exists.");
      }
      // Run the "validID" function in the "Check" class to see if the format is correct.
      Check.validID(replacementID);
      // If the ID in the stockList matches the old ID.
      if ((((BeanBag) stockList.get(i)).getID()).equals(oldID)) {
        // Set the ID in the stockList to the replacement ID.
        ((BeanBag) stockList.get(i)).setID(replacementID);
      }
      // Increment the "counter" integer by 1.
      counter++;
    }
    // If the counter is less than 1.
    if (counter < 1) {
      // Throw BeanBagIDNotRecognisedException as counter should never be less than 1.
      throw new BeanBagIDNotRecognisedException("No BeanBag with this ID.");
    }
  }
}
