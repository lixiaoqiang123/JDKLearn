package lang;

public class TypeTest {

    public static void main(String[] args) {
        Integer i1 = new Integer(100);
        Integer u1 = new Integer(100);
        System.out.println(i1==u1);
        Integer i = Integer.valueOf(100);
        Integer u = Integer.valueOf(100);
        System.out.println(i==u);
        IntegerWrapper integerWrapper = new IntegerWrapper();
        IntegerWrapper integerWrapper2 = new IntegerWrapper();
        integerWrapper.i = integerWrapper2.i= 200;
        System.out.println(integerWrapper.i.equals(integerWrapper2.i));
    }

    private static class IntegerWrapper {

        Integer i ;

        public Integer getI() {
            return i;
        }

        public void setI(Integer i) {
            this.i = i;
        }
    }
}
