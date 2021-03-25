package com.zlx.JUC;


import lombok.SneakyThrows;

import java.time.Instant;
import java.util.concurrent.*;

import static java.util.concurrent.Executors.defaultThreadFactory;

public class ThreadTest implements Runnable {
    public static Long k = 10000L;
    public Object object = new Object();

    @SneakyThrows
    @Override
    public void run() {
        while (true) {
            synchronized (object) {
//            CountDownLatch countDownLatch = new CountDownLatch(10);
                if (k > 0) {
                    k--;
                    System.out.println(Thread.currentThread().getName() + "正在卖票，还剩：k = " + k);
                } else {
                    System.out.println("票卖完了");
                    return;
                }
//                countDownLatch.countDown();
//                Thread.sleep(100);
            }

        }
    }
}

class ExecutePool {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start = Instant.now().toEpochMilli();
//        testThreadPool();
//        testThread();
        testNormal();
        long end = Instant.now().toEpochMilli();
        System.out.println("cost time :" + (end -start));
    }

    public static void testThreadPool() throws ExecutionException, InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                5,
                10,
                1,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(1000),
                defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());

        ThreadTest threadTest = new ThreadTest();
        for (int i = 0; i < 5; i++) {
            threadPoolExecutor.submit(threadTest);
        }
        threadPoolExecutor.shutdown();
        while (!threadPoolExecutor.isTerminated()){
//            System.out.println("not stop");
        }
    }


    public static void testNormal() {
        ThreadTest threadTest = new ThreadTest();
        long start = Instant.now().toEpochMilli();
        threadTest.run();
        long end = Instant.now().toEpochMilli();
        System.out.println("time = "+(end-start));
    }


    public static void testThread() {

        for (int i = 0; i < 5; i++) {
            ThreadTest threadTest = new ThreadTest();
            ThreadTest threadTest2 = new ThreadTest();
            ThreadTest threadTest3 = new ThreadTest();
            Thread t = new Thread(threadTest, "窗口" + i);
//            Thread t1 = new Thread(threadTest, "窗口" + 1);
//            Thread t2 = new Thread(threadTest, "窗口" + 2);
//            Thread t3 = new Thread(threadTest, "窗口" + 3);
            t.start();
//            t1.start();
//            t2.start();
//            t3.start();
        }
    }
}