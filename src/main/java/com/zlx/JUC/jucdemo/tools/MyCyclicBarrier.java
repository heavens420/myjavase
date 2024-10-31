package com.zlx.JUC.jucdemo.tools;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyCyclicBarrier {
    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
      test2();
    }

    public static void test1() {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(10);
        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < 10; i++) {
            executorService.execute(()->{
                System.out.println("before");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println("after");
            });
        }
        executorService.shutdown();
    }
    public static void test2() {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
            System.out.println("集齐七颗召唤神龙!");
        });

        for (int i = 0; i < 7; i++) {
            int finalI = i;
            new Thread(() ->{
                try {
//                    System.out.println("收集到了第"+(finalI +1)+"颗");
                    System.out.printf("收集到了第%d颗龙珠\n",finalI+1);
                    // 阻塞任务线程
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }
            }).start();
        }
    }
}
