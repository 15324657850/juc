package com.example.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wxl
 * 题目：三个售票员   卖出  30张票
 * <p>
 * 多线程编程企业套路+模板
 * <p>
 * <p>
 * 在高内聚低耦合的前提下  ，线程 操作  资源类
 */
public class SaleTicket {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        new Thread(() -> {
            ticket.sale();
        }, "AA").start();
        new Thread(() -> {
            ticket.sale();
        }, "BB").start();
        new Thread(() -> {
            ticket.sale();
        }, "CC").start();

        for (int i = 1; i <= 20; i++) {
            new Thread(() -> {

            }, String.valueOf(i)).start();
        }

    }
}


class Ticket {
    private int number = 30;
    private Lock lock = new ReentrantLock();

    public void sale() {
        lock.lock();
        try {
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + "\t卖出第" + number + "票");
                number--;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }


    }

}