package lang.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadTest {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 20, 1, TimeUnit.NANOSECONDS, new LinkedBlockingDeque<>());
        while (true){
            threadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("测试线程池，线程池测试");
                }
            });
        }
    }
}
