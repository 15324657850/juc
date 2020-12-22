package com.example.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wxl
 * 现在俩个线程，可以操作初始值为0的一个变量
 * 实现一个线程对该变量加一，一个线程对该变量减一
 * 实现交替  来10轮  变量初始值为0
 * <p>
 * 高内聚低耦合，线程操作资源类
 * 判断 干活  通知
 * 多线程交互中，必须防止多线程的虚假唤醒.也即（判断只能用while  不能用if）
 */
public class ThreadWaitNotifyDemo {
    public static void main(String[] args) throws Exception {
        AirCondition airCondition = new AirCondition();
        Lock lock=new ReentrantLock();


        new Thread(() -> {
            for (int i = 1; i < 11; i++) {
                try {
                    airCondition.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 1; i < 11; i++) {
                try {
                    airCondition.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 1; i < 11; i++) {
                try {
                    airCondition.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();

        new Thread(() -> {
            for (int i = 1; i < 11; i++) {
                try {
                    airCondition.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "D").start();

    }
}


class AirCondition {
    private int number = 0;

    public synchronized void increment() throws InterruptedException {
        //判断
        while (number != 0) {
            this.wait();
        }
        //干活
        number++;
        System.out.println(Thread.currentThread().getName() + "\t" + number);
        //通知
        this.notifyAll();

    }

    public synchronized void decrement() throws InterruptedException {
        while (number == 0) {
            this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName() + "\t" + number);
        this.notifyAll();
    }
}
