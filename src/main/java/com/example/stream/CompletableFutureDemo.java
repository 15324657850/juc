package com.example.stream;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author wxl
 */
public class CompletableFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "没有返回 update mysql ok");
        });
        completableFuture.get();

        CompletableFuture<Integer> completableFuture1 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "没有返回 insert mysql ok");
            //int a=10/0;
            return 1024;
        });
        completableFuture1.whenComplete((t,u)->{
            System.out.println("*********t   "+t);
            System.out.println("*********u   "+u);
        }).exceptionally(f->{
            System.out.println("****Exception"+f.getMessage());
            return 4444;
        }).get();
    }
}
