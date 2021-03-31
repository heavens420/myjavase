package com.zlx.JUC;

import java.util.concurrent.CountDownLatch;

/**
 * 必须在前n个线程执行完 才执行后面的任务
 */

public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
//        test1();
        test2();
    }

    public static void test1() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 0; i < 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"正在执行");
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"最后执行");
    }

    public static void test2() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(6);

        for (int i = 1; i < 7; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+"被灭");
                latch.countDown();
            },CountryEnum.forName(i).getName()).start();
        }
        latch.await();
        System.out.println(Thread.currentThread().getName()+"秦国统一天下");
    }
}
