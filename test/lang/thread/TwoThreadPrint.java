package lang.thread;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class TwoThreadPrint {

    AtomicInteger atomicInteger = new AtomicInteger();

    @Test
    public void test() {
        Thread1 thread1 = new Thread1();
        Thread2 thread2 = new Thread2();
        while (atomicInteger.get() < 200) {
            thread1.run();
            thread2.run();
        }
    }

    public class Thread1 implements Runnable {

        @Override
        public void run() {
            System.out.println("Thread1----" + atomicInteger.getAndIncrement());
        }
    }

    public class Thread2 implements Runnable {

        @Override
        public void run() {
            System.out.println("Thread2----" + atomicInteger.getAndIncrement());
        }
    }


    @Test
    public void test2() {
        AtomicInteger atomicInteger1 = new AtomicInteger();
        Object o = new Object();
        new Thread(() -> {
            try {
                while (atomicInteger1.get() < 200) {
                    synchronized (o) {
                        o.notify();
                        System.out.println("Thread1----" + atomicInteger1.getAndIncrement());
                        o.wait();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                while (atomicInteger1.get() < 200) {
                    synchronized (o) {
                        o.notify();
                        System.out.println("Thread2----" + atomicInteger1.getAndIncrement());
                        o.wait();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
