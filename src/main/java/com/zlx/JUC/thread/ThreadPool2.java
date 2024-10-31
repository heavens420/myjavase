package com.zlx.JUC.thread;


import com.alibaba.fastjson.JSONObject;
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.*;

/**
 * jdk自带线程池
 */
@Slf4j
public class ThreadPool2 {
    private static final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();


    public static void main(String[] args) throws InterruptedException, ExecutionException {
//        test1();
//        test2();
//        test3();
//        test5();
//        test8();
        List<String> list = null;

        List<String> o = Optional.ofNullable(list).orElse(new ArrayList<>());
        System.out.println(o);
    }

    /**
     * 线程池线程数量无限
     */
    public static void test1() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            log.info("begin execute。。。。。");
        });
    }

    /**
     * 指定线程池线程数量为10
     */
    public static void test2() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 20; i++) {
            int finalI = i;
            executorService.execute(() -> {
                log.info("Num:{}执行了", finalI);
            });
        }
    }

    /**
     * 延时执行线程
     */
    public static void test3() throws InterruptedException {
//        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        log.info("=======开始执行========={}", LocalDateTime.now());
        executorService.schedule(() -> {
            log.info("=======延时执行======={}", LocalDateTime.now());
        }, 3, TimeUnit.SECONDS);

        // fixRate: A任务执行即计时开始 到时间间隔 B任务执行
        // delayRate: A任务结束才开始计时 到时间间隔 B任务执行
        executorService.scheduleAtFixedRate(() -> {
            log.info("-------定时执行------");
        }, 2, 2, TimeUnit.SECONDS);

        // 阻塞main线程 等待定时任务线程执行完（定时任务线程 正常情况执行不玩）
//        Thread.currentThread().join();
        executorService.shutdown();
    }

    /**
     * 延时执行线程
     */
    public static void test4() {
        ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1);

        log.info("=======开始执行========={}", LocalDateTime.now());
        executorService.schedule(() -> {
            log.info("=======延时执行========={}", LocalDateTime.now());
        }, 3, TimeUnit.SECONDS);
        executorService.shutdown();
    }

    /**
     * 延时线程 只创建一个线程
     *
     * @throws InterruptedException
     */

    public static void test5() throws InterruptedException {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleWithFixedDelay(() -> {
            log.info("=======begin===1");
        }, 0, 2, TimeUnit.SECONDS);
        executorService.scheduleWithFixedDelay(() -> {
            log.info("=======begin===2");
        }, 0, 2, TimeUnit.SECONDS);
        executorService.scheduleWithFixedDelay(() -> {
            try {
                // 使用的都是同一个线程 当前线程被阻塞 其他也被阻塞
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            log.info("=======begin===3");
        }, 0, 2, TimeUnit.SECONDS);
        // 阻塞主线程
        Thread.currentThread().join();
        executorService.shutdown();
    }

    /**
     * 创建单个线程
     */
    public static void test6() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {
            log.info("=======begin===");
        });
        executorService.shutdown();
    }

    /**
     * 窃取线程，如果其他线程池有空闲线程 会使用其他线程池空闲线程执行任务
     */
    public static void test7() {
        ExecutorService executorService = Executors.newWorkStealingPool(3);
        executorService.execute(() -> {
            log.info("=======execute======");
        });
        executorService.shutdown();
    }


    public static void test8() throws InterruptedException, ExecutionException {
        @Cleanup("shutdown") ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        ScheduledFuture<LocalDateTime> scheduledFuture;
        for (int i = 0; i < 10; i++) {
            log.info("{}=======开始执行========={}", i, LocalDateTime.now());
            int finalI = i;
            if (i == 0) {
                scheduledFuture = executorService.schedule(() -> getInstant(finalI), 0, TimeUnit.SECONDS);
            } else {
                scheduledFuture = executorService.schedule(() -> getInstant(finalI), 3, TimeUnit.SECONDS);
            }
            LocalDateTime localDateTime = ((LocalDateTime) scheduledFuture.get());
            if (i == 3) {
                log.info("{}========执行完成========{}", i, localDateTime);
                break;
            }
        }

//        executorService.shutdown();
    }

    public static LocalDateTime getInstant(int i) {
        log.info("{}========执行中========{}", i, LocalDateTime.now());
        return LocalDateTime.now();
    }
}
