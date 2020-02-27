import beanbags.*;

public class TestingClassSam {
  public static void main(String[] args) throws IllegalIDException, BeanBagMismatchException, InvalidMonthException, IllegalNumberOfBeanBagsAddedException {
    Store testStore = new Store();
    BeanBag testBag = new BeanBag("NAME","Test", "TEST BAG", (short)2020, (byte)12);
    testStore.addBeanBags(10,"testing","Sam","RUN",(short)2020,(byte)12);
    for(BeanBag b : testStore.)
    {
      System.out.println(b.toString());
    }
    //System.out.println(testBag.toString());
  }
}
