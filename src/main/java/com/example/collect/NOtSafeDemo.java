package com.example.collect;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author wxl
 * 1. 故障现象
 * java.util.ConcurrentModificationException
 * 2.导致原因
 * 线程不安全
 * <p>
 * 3.解决方案
 * Vector
 * Collections.synchronizedList(new ArrayList<>())
 * new CopyOnWriteArrayList()
 */
public class NOtSafeDemo {
    public static void main(String[] args) {
        Map<String, Object> map = new ConcurrentHashMap<>();//Collections.synchronizedMap(new HashMap<>());//new HashMap<>();//16

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 8));
                System.out.println(map);
            }, String.valueOf(i)).start();
        }

        new HashMap<>();


    }

    private static void setNotSafe() {
        Set<String> set = new CopyOnWriteArraySet<>();//new HashSet<>();

        new HashSet<>();

        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(set);
            }, String.valueOf(i)).start();
        }
    }

    private static void listNotSafe() {
        //        List<String> list = new ArrayList<>();
//        List<String> list = new Vector<>();
//        List<String> list = Collections.synchronizedList(new ArrayList<>());
        List<String> list = new CopyOnWriteArrayList();


        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }
}
