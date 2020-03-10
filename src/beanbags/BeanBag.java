package beanbags;

/**
 * BeanBags is a class for implementing BeanBag objects
 *
 * @author 690027367 & 690025118
 * @version 0.1
 */
public class BeanBag {

  private String name;
  private String ID;
  private String manufacturer;
  private String information;
  private int price;
  private byte month;
  private short year;
  private boolean reserved;
  private int reservationNumber;

  public BeanBag(String name, String ID, String manufacturer, short year, byte month) {
    this.name = name;
    this.ID = ID;
    this.manufacturer = manufacturer;
    this.year = year;
    this.month = month;
    this.reserved = False;
  }

  public BeanBag(
      String name, String ID, String manufacturer, String information, short year, byte month) {
    this.name = name;
    this.ID = ID;
    this.manufacturer = manufacturer;
    this.information = information;
    this.year = year;
    this.month = month;
    this.reserved = False;
  }

  public String getName() {
    return name;
  }

  public String getID() {
    return ID;
  }

  public void setID(String ID) {
    this.ID = ID;
  }

  public String getManufacturer() {
    return manufacturer;
  }

  public String getInformation() {
    return information;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public short getYear() {
    return year;
  }

  public byte getMonth() {
    return month;
  }

  public boolean getReserved() {
    return reserved;
  }

  public int getReservationNumber() {
    return reservationNumber;
  }

  public void setReserved(boolean reserved) {
    this.reserved = reserved;
  }

  public void setReservationNumber(int reservationNumber) {
    this.reservationNumber = reservationNumber;
  }

  @Override
  public String toString() {
    return name
        + ","
        + ID
        + ","
        + manufacturer
        + ","
        + information
        + ","
        + price
        + ","
        + year
        + ","
        + month
        + ","
        + reserved
        + ","
        + reservationNumber
        + "\n";
  }
}
