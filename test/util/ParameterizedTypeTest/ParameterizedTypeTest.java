package util.ParameterizedTypeTest;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ParameterizedTypeTest {


    public static void main(String[] args) {
        Test test= new Test();
        Method[] methods = test.getClass().getDeclaredMethods();
        for (int i = 0; i < methods.length; i++) {
            Method method = methods[i];

            Type returnType = method.getGenericReturnType();

            if (returnType == null) {
                continue;
            }

            Class returnClass = (Class) returnType;
            System.out.println("Return type is " + returnClass.getName());
        }
    }


    static class Test {
        public List<String> getList() {
            ArrayList<String> list = new ArrayList<>();
            list.add("test");
            list.add("test1");
            return list;

        }

    }


}
