package beanbags;

import java.io.*;

/**
 * Implementor for the BeanBagStore Interface
 *
 * @author 690027367
 * @author 690025118
 * @version 0.1
 */
public class Store implements BeanBagStore {
  public static ObjectArrayList stockList = new ObjectArrayList();

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
    try {
      // Ensures Number of BeanBags is valid (if not >1 throw error)
      if (num >= 1) {
        for (int i = 1; i <= num; i++) {
          BeanBag tempBag = new BeanBag(name, id, manufacturer, year, month);
          stockList.add(tempBag);
        }
      } else {
        throw new IllegalNumberOfBeanBagsAddedException("Number of bags must be greater then 0.");
      }
    } catch (Exception e) {
      System.out.println(e);
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
    try {
      // Ensures Number of BeanBags is valid (if not >1 throw error)
      if (num >= 1) {
        for (int i = 1; i <= num; i++) {
          BeanBag tempBag = new BeanBag(name, id, manufacturer, information, year, month);
          stockList.add(tempBag);
        }
      } else {
        throw new IllegalNumberOfBeanBagsAddedException("Number of bags must be greater then 0.");
      }
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  public void setBeanBagPrice(String id, int priceInPence)

      // Modify the price of a bean bag object

      throws InvalidPriceException, BeanBagIDNotRecognisedException, IllegalIDException {}

  public void sellBeanBags(int num, String id)

      // Method for searching via "ID"

      // Check bean bag in stock,
      // Check bean bag not reserved,
      // Check sufficient stock for quantity required,
      // Remove from ObjectArrayList

      throws BeanBagNotInStockException, InsufficientStockException,
          IllegalNumberOfBeanBagsSoldException, PriceNotSetException,
          BeanBagIDNotRecognisedException, IllegalIDException {}

  public int reserveBeanBags(int num, String id)

      // Only reserve IF in stock
      // Generate "Reservation Number"?
      // Only sold to customer with matching reservation number.

      throws BeanBagNotInStockException, InsufficientStockException,
          IllegalNumberOfBeanBagsReservedException, PriceNotSetException,
          BeanBagIDNotRecognisedException, IllegalIDException {
    return 0;
  }

  public void unreserveBeanBags(int reservationNumber)

      // Check which bean bag is related to that reservation number
      // Remove the reservation number as no longer reserved.

      throws ReservationNumberNotRecognisedException {}

  public void sellBeanBags(int reservationNumber)

      // Method for searching via "reservationNumber"

      // Check bean bag in stock,
      // Check reservation number matches,
      // Check sufficient stock for quantity required,
      // Remove from ObjectArrayList

      throws ReservationNumberNotRecognisedException {}

  public int beanBagsInStock() {
    return 0;
  }

  // No parameters
  // Return entire list of stock?

  public int reservedBeanBagsInStock() {
    return 0;
  }

  // No parameters
  // Return list of stock where there is a reservation number

  public int beanBagsInStock(String id)

      // Return stock quantity of BeanBag with passed ID

      throws BeanBagIDNotRecognisedException, IllegalIDException {
    return 0;
  }

  public void saveStoreContents(String filename) throws IOException {

    // Loop through all things in "Stock"
    // Add each one to a text file or array?
    // Save as a text file with filename
      try {
          FileWriter writer = new FileWriter(filename);
          BufferedWriter bufferedWriter = new BufferedWriter(writer);

          for (int i = 0; i < stockList.size(); i++) {
              bufferedWriter.write(stockList.get(i).toString());
          }
          bufferedWriter.write("Hello World");
          //bufferedWriter.newLine();
          bufferedWriter.write("See You Again!");
          bufferedWriter.close();
      } catch (IOException e) {
          e.printStackTrace();
      }

  }

  /**
   * Method for loading stock list from a text file
   *
   * @param filename location of the file to be loaded
   * @throws IOException
   * @throws ClassNotFoundException
   */
  public void loadStoreContents(String filename) throws IOException, ClassNotFoundException {
    // TODO:
    //  https://www.reddit.com/r/javaexamples/comments/344kch/reading_and_parsing_data_from_a_file/
    //  Load text file with matching filename
    //  Read as a CSV
    //  For each line, create new object
    //  Add that object to ObjectArrayList

    // create a Buffered Reader object instance
    try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
      // read the first line from the text file
      String fileRead = br.readLine();
      // loop until all lines are read
      while (fileRead != null) {
        // use string.split to load a string array with the values from each line of
        // the file, using a comma as the delimiter
        // Use string . split t

        // NEED TO ADD RESERVATION NUM LATER
        // Name, ID, Manfuccatere, infomation, price, year, month

        String[] data = fileRead.split(",");

        // data[0] = name
        // data[1] = ID
        // data[2] = Manufactere
        // data[3] = infomation,
        // data[4] = price
        // data[5] = year
        // data[6] = month

        // if end of file reached
        fileRead = br.readLine();
      }
      // handle exceptions
    } catch (IOException ioe) {
      ioe.printStackTrace();
    }
  }

  public int getNumberOfDifferentBeanBagsInStock() {
    return 0;
  }

  public int getNumberOfSoldBeanBags() {
    return 0;
  }

  public int getNumberOfSoldBeanBags(String id)
      throws BeanBagIDNotRecognisedException, IllegalIDException {
    return 0;
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

  public void replace(String oldId, String replacementId)
      throws BeanBagIDNotRecognisedException, IllegalIDException {}
}
