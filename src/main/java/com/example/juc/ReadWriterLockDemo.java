package com.example.juc;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 多个线程同时读一个资源没有任何问题，所以为了满足并发量，读取共享资源应该可以同时进行
 * 但是 如果有一个线程想去写共享资源 就不应该再有其他线程可以对该资源进行都或者写
 * <p>
 * 读读  共存
 * 读写  不共存
 * 写写  不共存
 */
public class ReadWriterLockDemo {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();


        for (int i = 1; i < 5; i++) {
            int temp = i;
            new Thread(() -> {
                myCache.put(temp + "", temp);
            }, i + "").start();

        }
        for (int j = 1; j < 5; j++) {
            int temp = j;
            new Thread(() -> {
                myCache.get(temp + "");
            }, j + "").start();
        }
    }
}

class MyCache {
    private volatile Map<String, Object> map = new HashMap<>();
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void put(String key, Object val) {
         readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t写入数据" + key);
            Thread.sleep(300);
            map.put(key, val);
            System.out.println(Thread.currentThread().getName() + "\t写入完成");

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
             readWriteLock.writeLock().unlock();
        }

    }

    public void get(String key) {
         readWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t读取数据");
            Thread.sleep(300);

            Object result = map.get(key);
            System.out.println(Thread.currentThread().getName() + "\t读取完成" + result);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
             readWriteLock.readLock().unlock();
        }
    }

}