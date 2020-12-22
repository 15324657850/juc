package com.example.thread;

import java.util.concurrent.*;

/**
 * @author wxl
 */
public class MyThreadPoolDemo {
    public static void main(String[] args) {
        //cpu密集型   线程数+1
        //io密集型    cpu核数  1除以阻塞系数
//        System.out.println(Runtime.getRuntime().availableProcessors());
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                5,
                2L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),//阻塞队列
                Executors.defaultThreadFactory(),//线程工厂
                //拒绝策略
//                new ThreadPoolExecutor.AbortPolicy()
//                new ThreadPoolExecutor.CallerRunsPolicy()
//                new ThreadPoolExecutor.DiscardPolicy()
                new ThreadPoolExecutor.DiscardOldestPolicy()
        );
        try {
            //模拟有10个顾客过来银行办理业务，目前池子里面只有5个工作人员
            for (int i = 1; i <= 10; i++) {
                int temp=i;
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t办理业务"+temp);
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }

    }

    private static void initPool() {
        //        ThreadPoolExecutor
        //一个池子5个受理线程，类似一个一行有5个受理窗口
//        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        //一池一线程  一个一行一个受理窗口
        //    ExecutorService threadPool = Executors.newSingleThreadExecutor();
        //一池n线程  一个银行n个受理窗口
        ExecutorService threadPool = Executors.newCachedThreadPool();
        try {
            //模拟有10个顾客过来银行办理业务，目前池子里面只有5个工作人员
            for (int i = 1; i <= 10; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t办理业务");
                });
                try {
                    TimeUnit.SECONDS.sleep(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }


/*
    public ThreadPoolExecutor(int corePoolSize,//核心线程数  即常驻线程数
                              int maximumPoolSize,//最大线程数
                              long keepAliveTime,//多余线程的存活时间
                              TimeUnit unit, //keepAliveTime的单位
                              BlockingQueue<Runnable> workQueue,//任务队列，被提交但尚未被执行的任务 ，好比银行的等候区
                              ThreadFactory threadFactory,//线程池中工作的线程工厂，用于创建线程，一般默认
                              RejectedExecutionHandler handler)//拒绝策略，标识当队列满了，并且工作线程大于等于线程池的最大线程数，如何来拒绝
*/

}
