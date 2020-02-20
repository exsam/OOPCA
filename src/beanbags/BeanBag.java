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

    private int price;
    private short year;
    private byte month;

    public BeanBag(String ID, String manufacture, short year, byte month) {
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


}
