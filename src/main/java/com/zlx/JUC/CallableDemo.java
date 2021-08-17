package com.zlx.JUC;


import lombok.val;

import java.time.Instant;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CallableDemo implements Callable<Integer> {

    private Integer sum = 100;
    Lock lock = new ReentrantLock();

    @Override
    public Integer call() {
        lock.lock();
        try {
            if (sum <= 0) {
                return sum;
            }
            sum--;
            System.out.printf("当前线程：%s,sum = %d",Thread.currentThread().getName(),sum);
            System.out.println();
            return sum;
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CallableDemo callableDemo = new CallableDemo();
        Integer sum = 0;
        FutureTask<Integer> task = null;
        for (int i = 0; i < 1000; i++) {
            task = new FutureTask<>(callableDemo);
            new Thread(task).start();
        }
        sum = task.get();

        System.out.println("resut:"+sum);

        System.out.println(System.nanoTime());
        System.out.println(Instant.now().toEpochMilli());

    }
}
