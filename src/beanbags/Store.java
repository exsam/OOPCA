package beanbags;

// Import module "java.io".
import java.io.*;

/**
 * Implementor for the BeanBagStore Interface
 *
 * @author 690027367 & 690025118
 * @version 0.1
 */
public class Store implements BeanBagStore {

  // Define and initialise public static "ObjecetArrayList" called "stockList".
  public static ObjectArrayList stockList = new ObjectArrayList();

  // Define private integer variable "nextReservationNum" and set it's value to 0.
  private int nextReservationNum = 0;

  public void addBeanBags(
      int num, String manufacturer, String name, String id, short year, byte month)
      throws IllegalNumberOfBeanBagsAddedException, BeanBagMismatchException, IllegalIDException,
          InvalidMonthException {
    // Run the "validID" function in the "Check" class to see if the format is correct.
    Check.validID(id);
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

  private int GetNextResNum() {
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

  private void setReserved(String id, boolean reserved, int reservationNumber)
      throws ReservationNumberNotRecognisedException, BeanBagIDNotRecognisedException,
          IllegalIDException {
    // Loop through every object in the "stockList" object array list.
    for (int i = 0; i < stockList.size(); i++) {
      // If the ID of the beanBag in the stockList matches the passed parameter ID
      // AND the beanBag is NOT already reserved.
      if (((BeanBag) stockList.get(i)).getID().equals(id)
          && !((BeanBag) stockList.get(i)).getReserved()) {
        // Set the "getReserved" boolean in the "stockList" to the "reserved" boolean parameter.
        ((BeanBag) stockList.get(i)).setReserved(reserved);
        // Set the "reservationNumber" integer in the "stockList" to the "reservationNumber"
        // parameter.
        ((BeanBag) stockList.get(i)).setReservationNumber(reservationNumber);
        // Break from the loop.
        break;
      }
    }
  }

  private void setSold(String id, boolean sold)
      throws ReservationNumberNotRecognisedException, BeanBagIDNotRecognisedException,
          IllegalIDException {
    // Loop through every object in the "stockList" object array list.
    for (int i = 0; i < stockList.size(); i++) {
      // If the ID in the stockList matches the passed parameter ID.
      if (((BeanBag) stockList.get(i)).getID().equals(id)) {
        // Set the "setSold" boolean in the "stockList" to the "sold" boolean parameter.
        ((BeanBag) stockList.get(i)).setSold(sold);
        // Break from the loop.
        break;
      }
    }
  }

  public void sellBeanBags(int num, String id)
      throws BeanBagNotInStockException, InsufficientStockException,
          IllegalNumberOfBeanBagsSoldException, PriceNotSetException,
          BeanBagIDNotRecognisedException, IllegalIDException {
    Check.validID(id);
    if (num <= 0) {
      throw new IllegalNumberOfBeanBagsSoldException(
          "Please enter a quantity to be sold greater or equal to 1");
    }
    Check.fulfillRequest(num, id);
    for (int i = 0; i < num; i++) {
      // Loop through every object in the "stockList" object array list.
      for (int j = 0; j < stockList.size(); j++) {
        // If the current beanBag in the "stockList" isSold boolean is NOT true.
        if (!((BeanBag) stockList.get(j)).isSold() & ((BeanBag) stockList.get(j)).getID().equals(id)) {
          // If the ID in the stockList matches the passed parameter ID
            // Set the beanBag reserved state to "true" in the "stockList".
            ((BeanBag) stockList.get(j)).isSold(true);
            // Set the beanBag reservation number in the "stockList" to the value of the integer
            // "ReservationNum".
        }
      }
    }
    // int n = 0;

    // Method for searching via "ID"

    // while (n < num) {
    // for (int i = 0; i < stockList.size(); i++) {
    // BeanBag bag = (BeanBag) stockList.get(i);
    // System.out.println(bag.isSold());
    // if ((bag.getID()).equals(id) & !bag.getReserved() & !bag.isSold()) {
    // if (bag.getPrice() <= 0) {
    // throw new PriceNotSetException("No price set for BeanBag");
    // }
    // bag.setSold(true);
    // System.out.println(((BeanBag) stockList.get(i)).toString());
    // n = n + 1;
    // break;
    // }
    // }
    // }

    // Check bean bag in stock,
    // Check bean bag not reserved,
    // Check sufficient stock for quantity required,
    // Remove from ObjectArrayList
  }
  // TODO
  public int reserveBeanBags(int num, String id)
      throws BeanBagNotInStockException, InsufficientStockException,
          IllegalNumberOfBeanBagsReservedException, PriceNotSetException,
          BeanBagIDNotRecognisedException, IllegalIDException {

    // Define the string variable "ReservationNum" as the result from the function
    // "GetNextRestNum()".
    int ReservationNum = GetNextResNum();
    // Loop the following code "num" times.
    for (int i = 0; i < num; i++) {
      // Loop through every object in the "stockList" object array list.
      for (int j = 0; j < stockList.size(); j++) {
        // If the current beanBag in the "stockList" reserved state boolean is NOT true.
        if (!((BeanBag) stockList.get(j)).getReserved()) {
          // If the ID in the stockList matches the passed parameter ID
          if (((BeanBag) stockList.get(j)).getID().equals(id)) {
            // Set the beanBag reserved state to "true" in the "stockList".
            ((BeanBag) stockList.get(j)).setReserved(true);
            // Set the beanBag reservation number in the "stockList" to the value of the integer
            // "ReservationNum".
            ((BeanBag) stockList.get(j)).setReservationNumber(ReservationNum);
          }
        } else {
          // TODO PLACEHOLDER
        }
      }
    }
    // Return the value of the "ReservationNum" string variable.
    return ReservationNum;
  }

  public void unreserveBeanBags(int reservationNumber)
      throws ReservationNumberNotRecognisedException {
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
    // TODO
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
    // Define integer "BagStock" and set the value to 0.
    int BagStock = 0;
    // Loop through every object in the "stockList" object array list.
    for (int i = 0; i < stockList.size(); i++) {
      // If the ID in the stockList matches the passed parameter ID.
      if (((BeanBag) stockList.get(i)).getID().equals(id)) {
        // Increment the "BagStock" integer by 1.
        BagStock = BagStock + 1;
      }
    }
    // Return the value of the "BagStock" integer.
    return BagStock;
  }

  public void saveStoreContents(String filename) throws IOException {
    // Define a "writer" stream
    FileWriter writer = new FileWriter(filename, false);
    BufferedWriter bufferedWriter = new BufferedWriter(writer);
    // Loop through every object in the "stockList" object array list.
    for (int i = 0; i < stockList.size(); i++) {
      // Using the "writer" stream, add the data in the current ObjectArrayList
      // position in the "stockList" using the "toString()" method.
      bufferedWriter.write(stockList.get(i).toString());
    }
    // Close the "writer" stream.
    bufferedWriter.close();
  }

  public void loadStoreContents(String filename) throws IOException, ClassNotFoundException {
    // Define a "reader" to open the file "filename".
    BufferedReader br = new BufferedReader(new FileReader(filename));
    String fileRead = br.readLine();
    // Loop through each line in the file till a null (blank) line is found.
    while (fileRead != null) {

      // Define string array "data" where each element is separated by a comma in the text file.
      String[] data = fileRead.split(",");

      // Define and set all the variables from data stored in the "data" array,
      // all values have been stored (and cast when needed) to the correct datatype.
      String name = data[0];
      String id = data[1];
      String manufacturer = data[2];
      String information = data[3];
      int price = Integer.parseInt(data[4]);
      short year = Short.parseShort(data[5]);
      byte month = Byte.parseByte(data[6]);
      boolean reserved = Boolean.parseBoolean(data[7]);
      int reservationNumber = Integer.parseInt(data[8]);
      boolean sold = Boolean.parseBoolean(data[9]);

      // Try catch multi-block for exception handling.
      try {
        // Call the "addBeanBags" function and pass the needed parameters from the data array.
        addBeanBags(1, manufacturer, name, id, year, month, information);
        // Catch and print the relevant exceptions if they occur.
      } catch (Exception e) {
        System.out.println(e);
      }
      try {
        // Call the "setBeanBagPrice" function and pass the needed parameters from the data array.
        setBeanBagPrice(id, price);
        // Catch and print the relevant exceptions if they occur.
      } catch (Exception e) {
        System.out.println(e);
      }
      try {
        // Call the "setReserved" function and pass the needed parameters from the data array.
        setReserved(id, reserved, reservationNumber);
        // Catch and print the relevant exceptions if they occur.
      } catch (Exception e) {
        System.out.println(e);
      }
      try {
        // Call the "setSold" function and pass the needed parameters from the data array.
        setSold(id, sold);
        // Catch and print the relevant exceptions if they occur.
      } catch (ReservationNumberNotRecognisedException e) {
        e.printStackTrace();
      } catch (BeanBagIDNotRecognisedException e) {
        e.printStackTrace();
      } catch (IllegalIDException e) {
        e.printStackTrace();
      }
      // Read final line of text file.
      fileRead = br.readLine();
    }
  }

  public int getNumberOfDifferentBeanBagsInStock() {
    // Define integer "counter" and set the value to 0.
    int counter = 0;
    // Define integer "tracker" and set the value to 0.
    int tracker = 0;
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
        for (int y = 0; y < stockList.size(); y++) {
          // Set the element at position "counter" in "idArray" to the value of "ID".
          idArray[counter] = ID;
          // Increment the "counter" integer by 1.
          counter++;
          // Break from the loop.
          break;
        }
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
    // Define string "Info" with null value.
    String Info = "";
    // Loop through every object in the "stockList" object array list.
    for (int i = 0; i < stockList.size(); i++) {
      // If the ID in the stockList matches the passed parameter ID.
      if (((BeanBag) stockList.get(i)).getID().equals(id)) {
        // Set the "Info" string to the value stored in the "stockList".
        Info = ((BeanBag) stockList.get(i)).getInformation();
        // Condition met, so break from the loop.
        break;
      }
    }
    // Return the string "Info".
    return Info;
  }

  public void empty() {
    // Loop through every object in the "stockList" object array list.
    for (int i = 0; i < stockList.size(); i++) {
      // Remove the beanBag object at the current position in the "stockList".
      stockList.remove(i);
      // Set the global int "nextReservationNumber" to 0.
      nextReservationNum = 0;
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
