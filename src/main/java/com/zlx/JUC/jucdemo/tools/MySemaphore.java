package com.zlx.JUC.jucdemo.tools;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;


/**
 * Semaphore 的简单使用示例
 *          ：用于限制同时访问某些资源的数目
 */
public class MySemaphore {
    public static void main(String[] args) {
//        test();
        test2();
    }

    public static void test() {
        int clientCount = 3;
        ExecutorService executorService = Executors.newCachedThreadPool();
        Semaphore semaphore = new Semaphore(clientCount);

        for (int i = 0; i < 10; i++) {
            executorService.execute(()->{
                try {
                    semaphore.acquire();
                    System.out.print(semaphore.availablePermits()+" ");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }
            });
        }
        executorService.shutdown();
    }


    public static void test2() {
        // 现在允许操作的资源一共有2两个
        Semaphore sem = new Semaphore(3);
        // 模拟每一个用户办理业务的时间
        Random rand = new Random();
        for (int x = 0; x < 10; x++) {
            // 每一个线程就是一个要办理业务的人员
            new Thread(() -> {
                // 现在有空余窗口
//                if (sem.availablePermits() > 0) {
//                    System.out.println("【"+Thread.currentThread().getName()
//                            +"】进入银行，此时银行没有人排队"+sem.availablePermits());
//                } else {   // 没有空余位置
//                    System.out.println("【"+Thread.currentThread()
//                            .getName()+"】排队等待办理业务。"+sem.availablePermits());
//                }
                try {
                    // 从信号量中获得操作许可
                    sem.acquire();
                    System.out.println("【"+Thread.currentThread().getName()+"】｛start｝开始办理业务。");
                    // 模拟办公延迟
                    TimeUnit.SECONDS.sleep(rand.nextInt(5));
                    System.out.println("【"+Thread.currentThread().getName()+"】｛end｝结束办理业务。");
                    // 当前线程离开办公窗口
                    sem.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },"顾客-" + x).start();
        }
    }
}
