package beanbags;

import java.io.Serializable;

/**
 * BeanBags is a class for implementing BeanBag objects
 *
 * @author 690027367 & 690025118
 * @version 0.1
 */
public class BeanBag implements Serializable {

  // Set properties and relevant datatype's for BeanBag Object
  private String name;
  private String ID;
  private String manufacturer;
  private String information;
  private int price;
  private byte month;
  private short year;
  private boolean reserved;
  private int reservationNumber;
  private boolean isSold;

  // BeanBag Constructor Method (without Information).
  public BeanBag(String name, String ID, String manufacturer, short year, byte month) {
    this.name = name;
    this.ID = ID;
    this.manufacturer = manufacturer;
    this.year = year;
    this.month = month;
    this.reserved = false;
    this.isSold = false;
  }

  // BeanBag Constructor Method (with Information).
  public BeanBag(
      String name, String ID, String manufacturer, String information, short year, byte month) {
    this.name = name;
    this.ID = ID;
    this.manufacturer = manufacturer;
    this.information = information;
    this.year = year;
    this.month = month;
    this.reserved = false;
    this.isSold = false;
  }

  // BeanBag getter method for "name".
  public String getName() {
    return name;
  }

  // BeanBag getter method for "ID".
  public String getID() {
    return ID;
  }

  // BeanBag setter method for "ID".
  public void setID(String ID) {
    this.ID = ID;
  }

  // BeanBag getter method for "manufacturer".
  public String getManufacturer() {
    return manufacturer;
  }

  // BeanBag getter method for "information".
  public String getInformation() {
    return information;
  }

  // BeanBag getter method for "price".
  public int getPrice() {
    return price;
  }

  // BeanBag setter method for "price".
  public void setPrice(int price) {
    this.price = price;
  }

  // BeanBag getter method for "year".
  public short getYear() {
    return year;
  }

  // BeanBag getter method for "month".
  public byte getMonth() {
    return month;
  }

  // BeanBag getter method for "reserved" boolean state.
  public boolean getReserved() {
    return reserved;
  }

  // BeanBag setter method for "reserved" boolean state.
  public void setReserved(boolean reserved) {
    this.reserved = reserved;
  }

  // BeanBag getter method for "reservationNumber".
  public int getReservationNumber() {
    return reservationNumber;
  }

  // BeanBag setter method for "reservationNumber".
  public void setReservationNumber(int reservationNumber) {
    this.reservationNumber = reservationNumber;
  }

  // BeanBag getter method for "isSold" boolean state.
  public boolean isSold() {
    return isSold;
  }

  // BeanBag setter method for "isSold" boolean state.
  public void setSold(boolean sold) {
    isSold = sold;
  }

  @Override
  // toString() method used for writing to .txt file
  // Used to set beanBag data to a CSV format.
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
        + ","
        + isSold
        + "\n";
  }
}
