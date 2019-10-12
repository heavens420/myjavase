package com.zlx.JUC;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import static java.util.concurrent.Executors.*;
import static java.util.concurrent.TimeUnit.SECONDS;

public class ThreadPool {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
       ThreadPool tp = new ThreadPool();
//        tp.singlepool();
//        tp.seheduledpool();
//        tp.cachedpool();
//        tp.fixpool();
        tp.forkjionpooltest();
        System.out.println(Integer.MAX_VALUE); //  2^31-1
    }
    public void singlepool(){
        ExecutorService es = newSingleThreadExecutor();
        List<Thr> list = new ArrayList<Thr>();
        for (int i = 0; i < 1000; i++) {
            Thr t = new Thr();
            list.add(t);
        }

        for (int i = 0; i < 1000; i++) {
            es.execute(list.get(i));
        }
        es.shutdown();
    }

    public void fixpool(){
        ExecutorService es = Executors.newFixedThreadPool(10);
        List<Thr> list = new ArrayList<Thr>();
        for (int i = 0; i < 1000; i++) {
            Thr t = new Thr();
            list.add(t);
        }

        for (int i = 0; i < 1000; i++) {
            es.execute(list.get(i));
        }
        es.shutdown();
    }

    public void cachedpool(){
        ExecutorService cachepool = Executors.newCachedThreadPool();
        List<Thr> list = new ArrayList<Thr>();
        for (int i = 0; i < 1000; i++) {
            Thr t = new Thr();
            list.add(t);
        }

        for (int i = 0; i < 1000; i++) {
            cachepool.execute(list.get(i));
        }
        cachepool.shutdown();
    }
    public void seheduledpool() throws ExecutionException, InterruptedException {

        //此处必须用 ScheduledExecutorService 否则没有schedule 方法
        ScheduledExecutorService es = Executors.newScheduledThreadPool(3);
        List<Thr> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Thr t = new Thr();
            list.add(t);
        }

        for (int i = 0; i < 100; i++) {
            if (i%3 == 0){
                es.schedule(list.get(i),3, SECONDS); //延迟3 秒执行

                ScheduledFuture sf = es.schedule(new Callable<String>() {
                    @Override
                    public String call() throws Exception {
                        System.out.println("有返回值的 线程执行了");
                        return "Hello World";
                    }
                },3, SECONDS);
                System.out.println(sf.get());
                continue;
            }
            es.execute(list.get(i));
        }
        es.shutdown();
    }

    public void forkjionpooltest(){
        Thr t = new Thr();

        ExecutorService es = new ForkJoinPool(3);
        ForkJoinPool fj = new ForkJoinPool(3);
        fj.execute(t);
//        fj.invoke(t);
        es.shutdown();
    }
}

class Thr implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"正在运行 ");
    }
}
