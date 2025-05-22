package lang.reflect;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Constructor;

public class NewObjectByReflect {

    @Test
    public void byForName(){
        try {
            Class<?> aClass = Class.forName("lang.reflect.Person");
            Constructor<?> declaredConstructor = aClass.getDeclaredConstructor(String.class, String.class, String.class);
            Object o = declaredConstructor.newInstance("张三","12","嘻嘻");
            Assert.assertTrue(o instanceof Person);
            Assert.assertEquals("13",((Person) o).age);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void byGetClass(){
        Person person = new Person();
        Class<? extends Person> aClass = person.getClass();
    }
    @Test
    public void byClass(){
        Class<Person> personClass = Person.class;
    }
}
