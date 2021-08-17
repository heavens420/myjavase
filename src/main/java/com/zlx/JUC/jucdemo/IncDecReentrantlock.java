package com.zlx.JUC.jucdemo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class IncDecReentrantlock {
    private int i = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void inc() throws InterruptedException {
        lock.lock();
        try {
            while (i != 0){
                condition.await();
            }
            i++;
            System.out.println(Thread.currentThread().getName()+" "+i);
            condition.signalAll();
        }finally {
            lock.unlock();
        }

    }

    public void dec() throws InterruptedException {
        lock.lock();
        try {
            while (i == 0){
                condition.await();
            }
            i--;
            System.out.println(Thread.currentThread().getName()+" "+i);
            condition.signalAll();
        }finally {
            lock.unlock();
        }

    }

    public static void main(String[] args) {
        IncDecReentrantlock operation = new IncDecReentrantlock();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    operation.inc();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    operation.inc();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    operation.dec();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"C").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    operation.dec();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"D").start();
    }
}
