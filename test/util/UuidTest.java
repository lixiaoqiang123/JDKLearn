package util;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class UuidTest {

    public static void main(String[] args) {
        for (int i = 0; i < 200; i++) {
            UUID uuid = UUID.randomUUID();
            System.out.println(uuid);
        }
    }

    @Test
    private static void nameUUIDFromBytesTest() throws Exception {
        Random byteSource = new Random();
        byte[] someBytes = new byte[12];
        List list = new LinkedList();
        for (int i=0; i<100; i++) {
            byteSource.nextBytes(someBytes);
            UUID u1 = UUID.nameUUIDFromBytes(someBytes);
            if (3 != u1.version()) {
                throw new Exception("bad version");
            }
            if (2 != u1.variant()) {
                throw new Exception("bad variant");
            }
            if (list.contains(u1))
                throw new Exception("byte UUID collision very unlikely");
            list.add(u1);
        }
    }
}
