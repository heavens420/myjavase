package com.zlx.JUC.jucdemo;

import com.zlx.JUC.CountryEnum;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 必须在前n个线程执行完 才执行后面的任务
 */

public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
//        test1();
//        test2();
        test3();
    }

    public static void test1() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "正在执行");
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + "最后执行");
    }

    public static void test2() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(6);

        for (int i = 1; i < 7; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "被灭");
                latch.countDown();
            }, CountryEnum.forName(i).getName()).start();
        }
        latch.await();
        System.out.println(Thread.currentThread().getName() + "秦国统一天下");
    }

    public static void test3() throws InterruptedException {
        ExecutorService executorService = Executors.newWorkStealingPool(100);
        try {
            System.out.println("===========begin============");

            for (int k = 0; k < 500; k++) {
                CountDownLatch countDownLatch = new CountDownLatch(5000);

                System.out.println("===========for=============" + k);
                for (int j = 0; j < 5000; j++) {
                    int finalJ = j;
                    System.out.println("=====jjjjjjjj===" + j);
                    executorService.submit(() -> {
                        System.out.println(Thread.currentThread().getName() + finalJ);
                        countDownLatch.countDown();
                    });
                }
                countDownLatch.await();

            }

            System.out.println("================end================");
        } catch (Exception e) {

        }finally {
            System.out.println("-==========final==========");
        }

    }
}
