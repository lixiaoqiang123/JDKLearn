package spi;

import org.junit.Test;

import java.sql.Driver;
import java.util.Iterator;
import java.util.ServiceLoader;

public class SpiTest {

    @Test
    public void testSpi(){
        ServiceLoader<Driver> load = ServiceLoader.load(Driver.class);
        Iterator<Driver> iterator = load.iterator();
       while(iterator.hasNext()){
           System.out.println(iterator.next().getMajorVersion());
       }

    }
}
