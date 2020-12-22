package com.example.collect;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author wxl
 */
public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);

        /*System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));
//        System.out.println(blockingQueue.add("d"));

        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.add("e"));
        System.out.println(blockingQueue.add("r"));
        System.out.println(blockingQueue.element());*/

       /* System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        System.out.println(blockingQueue.offer("d"));
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());*/
   /*     blockingQueue.put("a");
        blockingQueue.put("b");
        blockingQueue.put("c");
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());*/
        System.out.println(blockingQueue.offer("e", 1, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("e", 1, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("e", 1, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("e", 1, TimeUnit.SECONDS));

    }
}
