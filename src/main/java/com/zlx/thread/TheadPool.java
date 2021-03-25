package com.zlx.thread;

import java.io.IOException;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * 手动创建线程池
 */
public class TheadPool {
    public static void main(String[] args) throws InterruptedException, IOException {
        int corePoolSize = 2;   // (无任务时)最大空闲数量
        int maximumPoolSize = 4; // 允许创建的最大线程数
        long keepAliveTime = 10;  // 超过核心线程数的空闲线程的最长生存时间  注意是空闲线程
        TimeUnit unit = TimeUnit.SECONDS;  // 上面的时间单位
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(2); // 阻塞队列 有4种

        // 若链表队列不给初始值 则maximumPoolSize参数将失效 最终大小以链表长度为准
//        BlockingQueue workQueue = new LinkedBlockingDeque(3);
        ThreadFactory threadFactory = new NameTreadFactory(); // 创建线程的工厂
        RejectedExecutionHandler handler = new MyIgnorePolicy();  // 拒绝策略
        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit,
                workQueue, threadFactory, handler);
        executor.prestartAllCoreThreads(); // 预启动所有核心线程

        for (int i = 1; i <= 100; i++) {
            MyTask task = new MyTask(String.valueOf(i));
            executor.execute(task);
        }

        System.in.read(); //阻塞主线程
    }

    static class NameTreadFactory implements ThreadFactory {

        private final AtomicInteger mThreadNum = new AtomicInteger(1);

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r, "my-thread-" + mThreadNum.getAndIncrement());
            System.out.println(t.getName() + " has been created");
            return t;
        }
    }

    public static class MyIgnorePolicy implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
            doLog(r, e);
        }

        private void doLog(Runnable r, ThreadPoolExecutor e) {
            // 可做日志记录等
            System.err.println(r.toString() + " rejected");
//          System.out.println("completedTaskCount: " + e.getCompletedTaskCount());
        }
    }

    static class MyTask implements Runnable {
        private String name;

        public MyTask(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            try {
                System.out.println(this.toString() + " is running!");
                Thread.sleep(100); //让任务执行慢点
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "MyTask [name=" + name + "]";
        }
    }
}
