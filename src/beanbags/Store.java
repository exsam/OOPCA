package beanbags;

import java.io.*;

/**
 * Implementor for the BeanBagStore Interface
 *
 * @author 690027367 & 690025118
 * @version 0.1
 */
public class Store implements BeanBagStore {
  public static ObjectArrayList stockList = new ObjectArrayList();
  private static ObjectArrayList soldList = new ObjectArrayList();

  // private static ObjectArrayList ReserveList = new ObjectArrayList();
  private int NextReservationNum = 1;

  /**
   * Method adds BeanBags to the stocklist.
   *
   * @param num number of bean bags added
   * @param manufacturer bean bag manufacturer
   * @param name bean bag name
   * @param id ID of bean bag
   * @param year year of manufacture
   * @param month month of manufacture
   * @throws IllegalNumberOfBeanBagsAddedException
   * @throws BeanBagMismatchException
   * @throws IllegalIDException
   * @throws InvalidMonthException
   */
  public void addBeanBags(
      int num, String manufacturer, String name, String id, short year, byte month)
      throws IllegalNumberOfBeanBagsAddedException, BeanBagMismatchException, IllegalIDException,
          InvalidMonthException {
    // TODO: need to check ID & Month Exceptions
    //  check ID is valid
    //  check ID matches other existing bags correctly

    // Ensures month entered is a valid month
    if (month <= 0 || month > 12) {
      throw new InvalidMonthException(
          month + " is an invalid month. Please enter a valid month 1-12");
    }

    // Ensures Number of BeanBags is valid (if not >1 throw error)
    if (num >= 1) {
      for (int i = 0; i < num; i++) {
        BeanBag tempBag = new BeanBag(name, id, manufacturer, year, month);
        stockList.add(tempBag);
      }
    } else {
      throw new IllegalNumberOfBeanBagsAddedException(
          "Number of bags must be must be a whole integer and greater then 0.");
    }
  }

  /**
   * Method adds BeanBags to the stocklist.
   *
   * @param num number of bean bags added
   * @param manufacturer bean bag manufacturer
   * @param name bean bag name
   * @param id ID of bean bag
   * @param year year of manufacture
   * @param month month of manufacture
   * @param information free text detailing bean bag information
   * @throws IllegalNumberOfBeanBagsAddedException
   * @throws BeanBagMismatchException
   * @throws IllegalIDException
   * @throws InvalidMonthException
   */
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
    // TODO: need to check ID & Month Exceptions
    //  check ID is valid
    //  check ID matches other existing bags correctly

    // Ensures month entered is a valid month
    if (month <= 0 || month > 12) {
      throw new InvalidMonthException(
          month + " is an invalid month. Please enter a valid month 1-12");
    }

    // Ensures number of BeanBags is valid (if not >1 throw error)
    if (num >= 1) {
      for (int i = 1; i <= num; i++) {
        BeanBag tempBag = new BeanBag(name, id, manufacturer, information, year, month);
        stockList.add(tempBag);
      }
    } else {
      throw new IllegalNumberOfBeanBagsAddedException(
          "Number of bags must be a whole integer and greater then 0.");
    }
  }

  private int GetNextResNum() {
    for (int i = 0; i < stockList.size(); i++) {
      if (((BeanBag) stockList.get(i)).getReservationNumber() > NextReservationNum) {
        NextReservationNum = ((BeanBag) stockList.get(i)).getReservationNumber() + 1;
      }
    }
    return NextReservationNum;
  }

  public void setBeanBagPrice(String id, int priceInPence)
      throws InvalidPriceException, BeanBagIDNotRecognisedException, IllegalIDException {
    if (priceInPence < 1) {
      throw new InvalidPriceException("Price must be 1 pence or higher");
    }
    for (int i = 0; i < stockList.size(); i++) {
      if (((BeanBag) stockList.get(i)).getID() == id
          && !((BeanBag) stockList.get(i)).getReserved()) {
        ((BeanBag) stockList.get(i)).setPrice(priceInPence);
      }
    }
  }

  private void setReserved(String id, boolean reserved, int reservationNumber)
      throws ReservationNumberNotRecognisedException, BeanBagIDNotRecognisedException,
          IllegalIDException {
    for (int i = 0; i < stockList.size(); i++) {
      //System.out.println("SetReserved Loop Runninig");
      //System.out.print("Stocklist ID = ");
      //System.out.print(((BeanBag) stockList.get(i)).getID());
      //System.out.print("Passed ID = ");
      //System.out.print(id);
      //System.out.print("\n");
      //System.out.println(((BeanBag) stockList.get(i)));
      if ( ((BeanBag) stockList.get(i)).getID().equals(id) && !((BeanBag) stockList.get(i)).getReserved())
      {
        ((BeanBag) stockList.get(i)).setReserved(reserved);
        ((BeanBag) stockList.get(i)).setReservationNumber(reservationNumber);
      }
    }
  }


  public void sellBeanBags(int num, String id)
      throws BeanBagNotInStockException, InsufficientStockException,
          IllegalNumberOfBeanBagsSoldException, PriceNotSetException,
          BeanBagIDNotRecognisedException, IllegalIDException {

    int n = 0;
    // Method for searching via "ID"
    while (n < num) {
      for (int i = 0; i < stockList.size(); i++) {
        if (((BeanBag) stockList.get(i)).getID() == id
            && !((BeanBag) stockList.get(i)).getReserved()) {
          BeanBag tempBag = ((BeanBag) stockList.get(i));
          soldList.add(tempBag);
          stockList.remove(i);
          n = n + 1;
          break;
        }
      }
    }
    // Check bean bag in stock,
    // Check bean bag not reserved,
    // Check sufficient stock for quantity required,
    // Remove from ObjectArrayList
  }

  public int reserveBeanBags(int num, String id)
      throws BeanBagNotInStockException, InsufficientStockException,
          IllegalNumberOfBeanBagsReservedException, PriceNotSetException,
          BeanBagIDNotRecognisedException, IllegalIDException {
    int ReservationNum = GetNextResNum();
    for (int i = 0; i < num; i++) {
      for (int j = 0; j < stockList.size(); j++) {
        if (((BeanBag) stockList.get(j)).getID().equals(id)) {
          ((BeanBag) stockList.get(j)).setReserved(true);
          ((BeanBag) stockList.get(j)).setReservationNumber(ReservationNum);
        }
      }
    }
    return ReservationNum;
  }

  public void unreserveBeanBags(int reservationNumber)
      throws ReservationNumberNotRecognisedException {
    for (int i = 0; i < stockList.size(); i++) {
      if (((BeanBag) stockList.get(i)).getReservationNumber() == reservationNumber) {
        ((BeanBag) stockList.get(i)).setReserved(false);
        ((BeanBag) stockList.get(i)).setReservationNumber(0);
      }
    }
  }

  public void sellBeanBags(int reservationNumber) throws ReservationNumberNotRecognisedException {
    // TODO
  }

  public int beanBagsInStock() {
    return stockList.size();
  }

  public int reservedBeanBagsInStock() {
    int ReservedStock = 0;
    for (int i = 0; i < stockList.size(); i++) {
      if (((BeanBag) stockList.get(i)).getReserved() == true) {
        ReservedStock = ReservedStock + 1;
      }
    }
    return ReservedStock;
  }

  public int beanBagsInStock(String id) throws BeanBagIDNotRecognisedException, IllegalIDException {
    int BagStock = 0;
    for (int i = 0; i < stockList.size(); i++) {
      if (((BeanBag) stockList.get(i)).getID().equals(id)) {
        BagStock = BagStock + 1;
      }
    }
    return BagStock;
  }

  /**
   * Method saves content of stockList ObjectArrayList to a txt file.
   *
   * @param filename location of the file to be saved
   * @throws IOException
   */
  public void saveStoreContents(String filename) throws IOException {

    // Loop through all things in "Stock"
    // Add each one to a text file or array?
    // Save as a text file with filename

    FileWriter writer = new FileWriter(filename, false);
    BufferedWriter bufferedWriter = new BufferedWriter(writer);
    // Loop through ObjectArrayList
    for (int i = 0; i < stockList.size(); i++) {
      bufferedWriter.write(stockList.get(i).toString());
    }
    bufferedWriter.close();
  }

  /**
   * Method for loading stock list from a text file
   *
   * @param filename location of the file to be loaded
   * @throws IOException
   * @throws ClassNotFoundException
   */
  public void loadStoreContents(String filename) throws IOException, ClassNotFoundException {
    BufferedReader br = new BufferedReader(new FileReader(filename));
    // read the first line from the text file
    String fileRead = br.readLine();
    // loop until all lines are read
    while (fileRead != null) {

      String[] data = fileRead.split(",");

      String name = data[0];
      String id = data[1];
      String manufacturer = data[2];
      String information = data[3];
      int price = Integer.parseInt(data[4]);
      short year = Short.parseShort(data[5]);
      byte month = Byte.parseByte(data[6]);

      // Temp Test Ryan
      boolean reserved = Boolean.parseBoolean(data[7]);
      int reservationNumber = Integer.parseInt(data[8]);

      try {
        addBeanBags(1, manufacturer, name, id, year, month, information);
      } catch (Exception e) {
        System.out.println(e);
      }
      try {
        System.out.println("SetBeanBagPrice");
        setBeanBagPrice(id, price);
      } catch (Exception e) {
        System.out.println(e);
      }
      try {
        setReserved(id, reserved, reservationNumber);
      } catch (Exception e) {
        System.out.println(e);
      }
      fileRead = br.readLine();
    }
  }

  public int getNumberOfDifferentBeanBagsInStock() {
    // TODO
    int counter = 0;
    String[] idArray = new String[stockList.size()];
    for (int i = 0; i < stockList.size(); i++) {
      String ID = ((BeanBag) stockList.get(i)).getID();
      for (int j = 1; j < stockList.size(); j++) {
        System.out.println(idArray[j] + " " + ID);
        if (ID == idArray[j]) {
          System.out.println("BREAK");
          break;
        }else{
          idArray[j] = ID;
          counter++;
        }
      }
      System.out.println("BROKEN");
    }
    return counter;
  }

  public int getNumberOfSoldBeanBags() {
    return soldList.size();
  }

  public int getNumberOfSoldBeanBags(String id)
      throws BeanBagIDNotRecognisedException, IllegalIDException {
    Check.validID(id);
    int counter = 0;
    for (int i = 0; i < soldList.size(); i++) {
      if (((BeanBag) soldList.get(i)).getID() == id) {
        counter++;
      }
    }
    if (counter == 0) {
      throw new BeanBagIDNotRecognisedException("No BeanBags with this ID have been sold.");
    }
    return counter;
  }

  public int getTotalPriceOfSoldBeanBags() {
    return 0;
  }

  public int getTotalPriceOfSoldBeanBags(String id)
      throws BeanBagIDNotRecognisedException, IllegalIDException {
    return 0;
  }

  public int getTotalPriceOfReservedBeanBags() {
    return 0;
  }

  public String getBeanBagDetails(String id)
      throws BeanBagIDNotRecognisedException, IllegalIDException {
    return "";
  }

  public void empty() {}

  public void resetSaleAndCostTracking() {}

  public void replace(String oldID, String replacementID)
      throws BeanBagIDNotRecognisedException, IllegalIDException {
    int counter = 0;
    for (int i = 0; i < stockList.size(); i++) {
      if (((BeanBag) stockList.get(i)).getID() == replacementID) {
        throw new IllegalIDException("This ID already exists.");
      }
      Check.validID(replacementID);
      if (((BeanBag) stockList.get(i)).getID() == oldID) {
        ((BeanBag) stockList.get(i)).setID(replacementID);
      }
      counter++;
    }
    if (counter < 1) {
      throw new BeanBagIDNotRecognisedException("No BeanBag with this ID.");
    }
  }

  private String intToHex(int number) {
    return Integer.toHexString(number);
  }
}
