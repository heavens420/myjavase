package com.zlx.JUC.jucdemo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrintlnOrder3 {
    public static void main(String[] args) {

    }

    public static void printLetter(){
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    lock.lock();
                    try {
                        for (int i = 0; i < 5; i++) {
                            System.out.printf("A");
                        }
                    }finally {
                        lock.unlock();
                    }

                }
            },"A").start();
    }

    public static void printB(){
        Lock lock = new ReentrantLock();
        lock.lock();
        try {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 10; i++) {
                        System.out.printf("A");
                    }
                }
            },"A").start();
        }finally {
            lock.unlock();
        }
    }
}
