package beanbags;

/**
 * BeanBags is a class for implementing BeanBag objects
 *
 * @author 690027367
 * @author 690025118
 * @version 0.1
 */

public class BeanBag {
    private String name;
    private String ID;
    private String manufacture;
    private String information;

    private int quantity;
    private int price;
    private short year;
    private byte month;

    private Boolean reserved;

    public BeanBag(String name, String ID, String manufacture, short year, byte month) {
        this.name = name;
        this.ID = ID;
        this.manufacture = manufacture;
        this.year = year;
        this.month = month;
    }

    public BeanBag(String name, String ID, String manufacture, String information, short year, byte month) {
        this.name = name;
        this.ID = ID;
        this.manufacture = manufacture;
        this.information = information;
        this.year = year;
        this.month = month;
    }

    public String getName() {
        return name;
    }

    public String getID() {
        return ID;
    }

    public String getManufacture() {
        return manufacture;
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

    @Override
    public String toString() {
        return "BeanBag{" +
                "name='" + name + '\'' +
                ", ID='" + ID + '\'' +
                ", manufacture='" + manufacture + '\'' +
                ", information='" + information + '\'' +
                ", price=" + price +
                ", year=" + year +
                ", month=" + month +
                '}';
    }
}
