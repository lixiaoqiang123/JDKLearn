package util;

public class StaticCodeTest {


    static {
        System.out.println("zzzzz");
    }

    {
        System.out.println("xxxxx");
    }

    public static void main(String[] args) {
//        StaticCodeTest staticCodeTest = new StaticCodeTest();
        System.out.println("11111");
    }
}
