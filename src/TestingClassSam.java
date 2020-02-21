import beanbags.BeanBag;

public class TestingClassSam {
  public static void main(String[] args) {
    BeanBag testBag = new BeanBag("Test", "TEST BAG", (short)2020, (byte)12);
    System.out.println(testBag.toString());
  }
}
