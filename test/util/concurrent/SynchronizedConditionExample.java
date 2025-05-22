package util.concurrent;

public class SynchronizedConditionExample {
    private static final Object lock = new Object();
    private static int count = 0;
    private static final int MAX_CAPACITY = 10;

    public static void main(String[] args) throws InterruptedException {
        Thread producer = new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    while (count == MAX_CAPACITY) {
                        try {
                            lock.wait(); // 等待消费者消费
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    count++;
                    System.out.println("生产者生产，当前数量: " + count);
                    lock.notifyAll(); // 通知消费者
                }
            }
        });

        Thread consumer = new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    while (count == 0) {
                        try {
                            lock.wait(); // 等待生产者生产
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    count--;
                    System.out.println("消费者消费，当前数量: " + count);
                    lock.notifyAll(); // 通知生产者
                }
            }
        });

        producer.start();
        consumer.start();
    }
}

