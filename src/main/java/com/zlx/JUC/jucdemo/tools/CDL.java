package com.zlx.JUC.jucdemo.tools;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CDL {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            executorService.execute(() -> {
                System.out.println("run...");
                countDownLatch.countDown();
            });
        }
        // 先执行完前面的十个线程 再执行主线程的打印
        countDownLatch.await();
        System.out.println("done");
        executorService.shutdown();
    }
}
