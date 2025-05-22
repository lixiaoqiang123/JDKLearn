package nio;

import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * 数组实现的阻塞队列
 */
public class ArrayBlockingQueueTest {

    @Test
    public void queueTest(){
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<String>(1024);
    }
}
