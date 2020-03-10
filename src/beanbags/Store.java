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
    private static ObjectArrayList soldList = new ObjectArrayList();

    private int NextReservationNum = 1;

    //private static ObjectArrayList ReserveList = new ObjectArrayList();

    String[] IDs;

    /**
     * Method adds BeanBags to the stocklist.
     *
     * @param num          number of bean bags added
     * @param manufacturer bean bag manufacturer
     * @param name         bean bag name
     * @param id           ID of bean bag
     * @param year         year of manufacture
     * @param month        month of manufacture
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
                for (int i = 1; i <= num; i++) {
                    BeanBag tempBag = new BeanBag(name, id, manufacturer, year, month);
                    stockList.add(tempBag);
                }
            } else {
                throw new IllegalNumberOfBeanBagsAddedException(
                        "Number of bags must be must be a whole integer and greater " + "then 0.");
                // throw other exception if method is not met
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Method adds BeanBags to the stocklist.
     *
     * @param num          number of bean bags added
     * @param manufacturer bean bag manufacturer
     * @param name         bean bag name
     * @param id           ID of bean bag
     * @param year         year of manufacture
     * @param month        month of manufacture
     * @param information  free text detailing bean bag information
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
        try {

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
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public int GetNextResNum()
    {
        for (int i = 0; i < stockList.size(); i++) {
            if (((BeanBag) stockList.get(i)).getReservationNumber() > NextReservationNum)
            {
                NextReservationNum = ((BeanBag) stockList.get(i)).getReservationNumber() + 1;
            }
        }
        return NextReservationNum;
    }

    public void setBeanBagPrice(String id, int priceInPence) {
        // throws InvalidPriceException, BeanBagIDNotRecognisedException, IllegalIDException {
        for (int i = 0; i < stockList.size(); i++) {
            if (((BeanBag) stockList.get(i)).getID() == id
                    && !((BeanBag) stockList.get(i)).getReserved()) {
                ((BeanBag) stockList.get(i)).setPrice(priceInPence);
            }
        }
    }

    public void sellBeanBags(int num, String id)
            throws BeanBagNotInStockException, InsufficientStockException,
            IllegalNumberOfBeanBagsSoldException, PriceNotSetException,
            BeanBagIDNotRecognisedException, IllegalIDException {

        try {
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
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public int reserveBeanBags(int num, String id)
<<<<<<< Updated upstream

        // Ryan White 10/3/2020
=======
            throws ReservationNumberNotRecognisedException {
        // Only reserve IF in stock
        // Generate "Reservation Number"?
        // Only sold to customer with matching reservation number.
>>>>>>> Stashed changes

            throws BeanBagNotInStockException, InsufficientStockException,
            IllegalNumberOfBeanBagsReservedException, PriceNotSetException,
            BeanBagIDNotRecognisedException, IllegalIDException {

        try {
            for (int i = 0; i < num; i++) {
                for (int j = 0; j < stockList.size(); j++) {
                    if (((BeanBag) stockList.get(j)).getID().equals(id))
                    {
                        ((BeanBag) stockList.get(j)).setReserved(true);
                        ((BeanBag) stockList.get(j)).setReservationNumber(GetNextResNum());
                    }
                }
            }
            System.out.print("Next Reservation number is: ");
            System.out.print(GetNextResNum());
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return 0;
    }


    public void unreserveBeanBags(int reservationNumber)
=======
          }



      }

  public void sellBeanBags(int reservationNumber)
>>>>>>> Stashed changes

        // Ryan White 9/3/2020

            throws ReservationNumberNotRecognisedException {
        try
        {
            for (int i = 0; i < stockList.size(); i++)
            {
                if ( ((BeanBag) stockList.get(i)).getReservationNumber() == reservationNumber )
                {
                    ((BeanBag) stockList.get(i)).setReserved(false);
                    ((BeanBag) stockList.get(i)).setReservationNumber(0);
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public void sellBeanBags(int reservationNumber)

        // R

        // Method for searching via "reservationNumber"

        // Check bean bag in stock,
        // Check reservation number matches,
        // Check sufficient stock for quantity required,
        // Remove from ObjectArrayList

            throws ReservationNumberNotRecognisedException {



    }

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

    public int beanBagsInStock(String id) throws BeanBagIDNotRecognisedException, IllegalIDException {
        // Return stock quantity of BeanBag with passed ID
        return 0;
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
        try {
            FileWriter writer = new FileWriter(filename);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            // Loop through ObjectArrayList
            for (int i = 0; i < stockList.size(); i++) {
                bufferedWriter.write(stockList.get(i).toString());
            }
            bufferedWriter.close();
        } catch (Exception e) {
            System.out.println(e);
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
        //  https://www.reddit.com/r/javaexamples/comments/344kch/reading_and_parsing_data_from_a_file/
        // create a Buffered Reader object instance
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
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
                try {
                    addBeanBags(1, name, id, manufacturer, year, month, information);
                } catch (Exception e) {
                    System.out.println(e);
                }

                fileRead = br.readLine();
            }
            // handle exceptions
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public int getNumberOfDifferentBeanBagsInStock() {
        int counter = 0;
        String[] idArray = new String[stockList.size()];
        for (int i = 0; i < stockList.size(); i++) {
            String ID = ((BeanBag) stockList.get(i)).getID();
            if (ID == "") {
                continue;
            } else {
                idArray[i] = ID;
                counter++;
            }
        }
        return counter;
    }

    public int getNumberOfSoldBeanBags() {
        return soldList.size();
    }

    public int getNumberOfSoldBeanBags(String id)
            throws BeanBagIDNotRecognisedException, IllegalIDException {
        try {
            if (!validateIDFormat(id)) {
            }
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
        } catch (Exception e) {
            System.out.println(e);
            return 0;
        }
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

    public void empty() {
    }

    public void resetSaleAndCostTracking() {
    }

  public void replace(String oldId, String replacementId)
      throws BeanBagIDNotRecognisedException, IllegalIDException {
    for (int i = 0; i < stockList.size(); i++) {
      if (((BeanBag) stockList.get(i)).getID() == oldId) {
        //TODO
      }
    }

  private boolean validateIDFormat(String id) {
    if (String.format(id, "0xFFFFFF") == "0xFFFFF") {
      return true;
      //TODO
    }
}
