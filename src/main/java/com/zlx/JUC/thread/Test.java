package com.zlx.JUC.thread;

import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {
    public static void main(String[] args) throws InterruptedException {
      testJoin();
    }

    public static void testCountDownLatch() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(10);
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        Thread thread = new Thread(() -> {
            System.out.println(new Date());
            latch.countDown();
        });

        for (int i = 0; i < 10; i++) {
            executorService.execute(thread);
        }

        latch.await();
        executorService.shutdown();
        System.out.println("over");
    }

    public static void testJoin() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            Thread t1 = new Thread(() -> {
            System.out.println("t1"+ finalI);
        });
        t1.start();
            t1.join();

        }

//        Thread.currentThread().join();
        System.out.println("------main-----------");
    }
}
