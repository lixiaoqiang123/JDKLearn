package util.concurrent;

import java.util.concurrent.Callable;

/**
 * Thread、Runnable、Callable、Future、FutureTask、RunnableFuture
 */
public class FutureTask {

    /**
     * Thread实现了Runable接口，实现了run方法
     * Callable 接口的 call 实现了线程执行返回结果，抛出异常
     * FutureTask实现了RunnableFuture 接口  ，RunnableFuture接口继承了Runnable, Future接口
     * FutrueTask可以返回结果也是依赖其中的Callable变量
     *
     * Future提供了三种功能：判断任务是否完成、能够中断任务、能够获取任务执行的结果
     */

    public static void main(String[] args) {

        /**
         * 接口实例化？  相当于创建了一个实现了Callable的匿名内部类的对象
         */
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                // 执行业务逻辑 ...
                int i = 1 / 0;
                return "this is Callable is running";
            }
        };
        try {
            String call = callable.call();
            System.out.println(call);
        }catch (Exception e){
            System.out.println(e);
        }


    }
}
