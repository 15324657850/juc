package com.example.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @author wxl
 * 多线程中 ，获得多线程的方式
 */
public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        myThread myThread = new myThread();

        FutureTask futureTask = new FutureTask(myThread);

        new Thread(futureTask, "A").start();
        System.out.println(Thread.currentThread().getName()+"\t  计算完成");
        System.out.println(futureTask.get());


    }
}


class myThread implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("-----come in  here ----");
        TimeUnit.SECONDS.sleep(4);
        return 1024;

    }
}

