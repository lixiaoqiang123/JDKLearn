package util.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 在 Java 中，Condition 是一种用于线程间协调的工具类，通常与 Lock（如 ReentrantLock）配合使用，
 * 用来替代传统的 Object 的 wait() 和 notify() 方法。它的核心方法是 await() 和 signal()，分别对应 Object 的 wait() 和 notify()。
 *
 * 1. Condition 的基本概念
 *      await(): 让当前线程等待，直到被通知（signal）或被中断。相当于 Object 的 wait() 方法。
 *      signal(): 唤醒在该 Condition 上等待的一个线程，相当于 Object 的 notify() 方法。
 *      signalAll(): 唤醒所有在该 Condition 上等待的线程，相当于 Object 的 notifyAll() 方法。
 * 2. 使用步骤
 *      要使用 Condition，需要先获取一个显式的锁（通常是 ReentrantLock），并通过锁生成一个 Condition 对象。
 * 注意事项
 *      必须持有锁: 调用 await() 或 signal() 方法前，线程必须先获取与该 Condition 关联的锁，否则会抛出 IllegalMonitorStateException。
 *      释放锁: 调用 await() 后，线程会释放锁，直到被唤醒后再次尝试获取锁。
 *      避免死锁: 如果使用不当，可能会导致线程永久等待。
 */
public class ConditionExample {
    private static Lock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            lock.lock();
            try {
                System.out.println("Thread 1: 等待信号...");
                condition.await(); // 线程进入等待状态，释放锁
                System.out.println("Thread 1: 收到信号，继续执行...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });

        Thread thread2 = new Thread(() -> {
            lock.lock();
            try {
                System.out.println("Thread 2: 准备发送信号...");
                condition.signal(); // 唤醒一个等待的线程
                System.out.println("Thread 2: 信号已发送...");
            } finally {
                lock.unlock();
            }
        });

        thread1.start();
        try {
            Thread.sleep(1000); // 确保 thread1 先运行并等待
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread2.start();
    }
}
