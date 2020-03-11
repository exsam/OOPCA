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

  private int nextReservationNum = 0;

  public void addBeanBags(
      int num, String manufacturer, String name, String id, short year, byte month)
      throws IllegalNumberOfBeanBagsAddedException, BeanBagMismatchException, IllegalIDException,
          InvalidMonthException {
    Check.validID(id);
    // Ensures month entered is a valid month
    if (month <= 0 || month > 12) {
      throw new InvalidMonthException(
          month + " is an invalid month. Please enter a valid month 1-12");
    }

    // Ensures Number of BeanBags is valid (if not >1 throw error)
    if (num >= 1) {
      for (int i = 0; i < num; i++) {
        BeanBag tempBag = new BeanBag(name, id, manufacturer, year, month);
        Check.matchingIDs(tempBag,stockList);
        stockList.add(tempBag);
      }
    } else {
      throw new IllegalNumberOfBeanBagsAddedException(
          "Number of bags must be must be a whole integer and greater then 0.");
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
    Check.validID(id);
    // Ensures month entered is a valid month
    if (month <= 0 || month > 12) {
      throw new InvalidMonthException(
          month + " is an invalid month. Please enter a valid month 1-12");
    }

    // Ensures number of BeanBags is valid (if not >1 throw error)
    if (num >= 1) {
      for (int i = 1; i <= num; i++) {
        BeanBag tempBag = new BeanBag(name, id, manufacturer, information, year, month);
        Check.matchingIDs(tempBag,stockList);
        stockList.add(tempBag);
      }
    } else {
      throw new IllegalNumberOfBeanBagsAddedException(
          "Number of bags must be a whole integer and greater then 0.");
    }
  }

  private int GetNextResNum() {
    for (int i = 0; i < stockList.size(); i++) {
      if (((BeanBag) stockList.get(i)).getReservationNumber() > nextReservationNum) {
        nextReservationNum = ((BeanBag) stockList.get(i)).getReservationNumber();
      }
    }
    nextReservationNum = nextReservationNum + 1;
    return nextReservationNum;
  }

  public void setBeanBagPrice(String id, int priceInPence)
      throws InvalidPriceException, BeanBagIDNotRecognisedException, IllegalIDException {
    Check.validID(id);
    if (priceInPence < 1) {
      throw new InvalidPriceException("Price must be 1 pence or higher");
    }
    int counter = 0;
    for (int i = 0; i < stockList.size(); i++) {
      if (((BeanBag) stockList.get(i)).getID() == id
          && !((BeanBag) stockList.get(i)).getReserved()) {
        ((BeanBag) stockList.get(i)).setPrice(priceInPence);
        counter++;
      }
    }
    if (counter <= 0) {
      throw new BeanBagIDNotRecognisedException("No BeanBags with ID: " + id + " were found.");
    }
  }

  private void setReserved(String id, boolean reserved, int reservationNumber)
      throws ReservationNumberNotRecognisedException, BeanBagIDNotRecognisedException,
          IllegalIDException {
    for (int i = 0; i < stockList.size(); i++) {
      if (((BeanBag) stockList.get(i)).getID().equals(id)
          && !((BeanBag) stockList.get(i)).getReserved()) {
        ((BeanBag) stockList.get(i)).setReserved(reserved);
        ((BeanBag) stockList.get(i)).setReservationNumber(reservationNumber);
        break;
      }
    }
  }

  private void setSold(String id, boolean sold)
      throws ReservationNumberNotRecognisedException, BeanBagIDNotRecognisedException,
          IllegalIDException {
    for (int i = 0; i < stockList.size(); i++) {
      if (((BeanBag) stockList.get(i)).getID().equals(id)) {
        ((BeanBag) stockList.get(i)).setSold(sold);
        break;
      }
    }
  }

  public void sellBeanBags(int num, String id)
      // TODO
      throws BeanBagNotInStockException, InsufficientStockException,
          IllegalNumberOfBeanBagsSoldException, PriceNotSetException,
          BeanBagIDNotRecognisedException, IllegalIDException {
    Check.validID(id);
    if (num <= 0) {
      throw new IllegalNumberOfBeanBagsSoldException(
          "Please enter a quantity to be sold greater or equal to 1");
    }
    int n = 0;
    // Method for searching via "ID"
    while (n < num) {
      for (int i = 0; i < stockList.size(); i++) {
        BeanBag bag = (BeanBag) stockList.get(i);
        if (bag.getID() == id & !bag.getReserved() & !bag.isSold()) {
          if (bag.getPrice() <= 0) {
            throw new PriceNotSetException("No price set for BeanBag");
          }
          bag.setSold(true);
          System.out.println(((BeanBag) stockList.get(i)).toString());
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
    //
    for (int i = 0; i < num; i++) {
      for (int j = 0; j < stockList.size(); j++) {
        if (!((BeanBag) stockList.get(j)).getReserved()){
          //System.out.println("\nNot Reserved!");
          if ( ((BeanBag) stockList.get(j)).getID().equals(id) ) {
            ((BeanBag) stockList.get(j)).setReserved(true);
            ((BeanBag) stockList.get(j)).setReservationNumber(ReservationNum);
          }
        }
        else{
          //System.out.println("\n Already Reserved!!!");
        }
        //System.out.println("\nReserve state: " + ((BeanBag) stockList.get(j)).getReserved());
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
    int counter = 0;
    for (int i = 0; i < stockList.size(); i++) {
      if(!((BeanBag)stockList.get(i)).isSold()){
        counter++;
      }
    }
    return counter;
  }

  public int reservedBeanBagsInStock() {
    int ReservedStock = 0;
    for (int i = 0; i < stockList.size(); i++) {
      if (((BeanBag) stockList.get(i)).getReserved() == true) {
        //System.out.print("\n The following beanbag is reserved: \n");
        //System.out.print(((BeanBag) stockList.get(i)).getID());
        //System.out.print("\n");
        ReservedStock = ReservedStock + 1;
      }
    }
    //System.out.print("\n *The Reserved stock is: " + ReservedStock);
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
    int counter = 0;
    int tracker = 0;
    String[] idArray = new String[stockList.size()];
    for (int i = 0; i < stockList.size(); i++) {
      String ID = ((BeanBag) stockList.get(i)).getID();
      tracker = 0;
      for (int j = 0; j < stockList.size(); j++) {
        if (ID.equals(idArray[j])) {
          tracker += 1;
        }
      }
      if (tracker == 0) {
        for (int y = 0; y < stockList.size(); y++) {
          idArray[counter] = ID;
          counter ++;
          break;
        }
      }
    }
    System.out.println(counter);
    return counter;
  }

  public int getNumberOfSoldBeanBags() {
    int counter = 0;
    for (int i = 0; i < stockList.size(); i++) {
      if (((BeanBag) stockList.get(i)).isSold()) {
        counter++;
      }
    }
    return counter;
  }

  public int getNumberOfSoldBeanBags(String id)
      throws BeanBagIDNotRecognisedException, IllegalIDException {
    Check.validID(id);
    int counter = 0;
    for (int i = 0; i < stockList.size(); i++) {
      if (((BeanBag) stockList.get(i)).getID() == id & ((BeanBag) stockList.get(i)).isSold()) {
        counter++;
      }
    }
    if (counter == 0) {
      throw new BeanBagIDNotRecognisedException("No BeanBags with this ID have been sold.");
    }
    return counter;
  }

  public int getTotalPriceOfSoldBeanBags() {
    int salesTotal = 0;
    for (int i = 0; i < stockList.size(); i++) {
      if (((BeanBag) stockList.get(i)).isSold()) {
        salesTotal += ((BeanBag) stockList.get(i)).getPrice();
      }
    }
    return salesTotal;
  }

  public int getTotalPriceOfSoldBeanBags(String id)
      throws BeanBagIDNotRecognisedException, IllegalIDException {
    int salesTotal = 0;
    Check.validID(id);
    for (int i = 0; i < stockList.size(); i++) {
      if (((BeanBag) stockList.get(i)).getID() == id & ((BeanBag) stockList.get(i)).isSold()) {
        salesTotal += ((BeanBag) stockList.get(i)).getPrice();
      }
    }
    if (salesTotal == 0) {
      throw new BeanBagIDNotRecognisedException("No BeanBags with this ID have been sold.");
    }
    return salesTotal;
  }

  public int getTotalPriceOfReservedBeanBags() {
    return 0;
  }

  public String getBeanBagDetails(String id)
      throws BeanBagIDNotRecognisedException, IllegalIDException {
    return "";
  }

  public void empty() {
    for (int i = 0; i < stockList.size(); i++) {
      stockList.remove(i);
      nextReservationNum = 0;
    }
  }

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
}
