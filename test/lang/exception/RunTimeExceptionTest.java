package lang.exception;

public class RunTimeExceptionTest {

    public static void main(String[] args) {
        try {
            test1(2);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    public static void test1(int i){
        System.out.println("test1--------1");
        test2(i);
        System.out.println("test1--------2");
    }


    public static void test2(int i){
        if(i>1){
            throw new CustomizedException("测试报错");
        }
    }
}
