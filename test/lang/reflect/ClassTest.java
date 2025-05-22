package lang.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class ClassTest {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class c1 = Class.forName("lang.reflect.Employee");   // 第1种，forName 方式获取Class对象
        Class c2 = Employee.class;      // 第2种，直接通过类获取Class对象
        Employee employee = new Employee("小明", "18", "写代码", 1, "Java攻城狮", 100000);
        Class c3 = employee.getClass();    // 第3种，通过调用对象的getClass()方法获取Class对象
        Constructor constructor = Employee.class.getConstructor(String.class, String.class, String.class);
        Class c4 = constructor.newInstance("张三", "19", "sex").getClass();
        System.out.println(c4);
        if (c1 == c2 && c1 == c3) {     // 可以通过 == 比较Class对象是否为同一个对象
            System.out.println("c1、c2、c3 为同一个对象");
            System.out.println(c1);     // class reflect.Employee
        }
        for (Method declaredMethod : c1.getDeclaredMethods()) {
            for (Parameter parameter : declaredMethod.getParameters()) {
                System.out.println(parameter.getType());
            }
        }

    }
}
