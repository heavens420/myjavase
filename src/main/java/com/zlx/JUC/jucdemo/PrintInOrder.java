package com.zlx.JUC.jucdemo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * juc 工具类实现顺序打印
 */
public class PrintInOrder {

    Lock lock = new ReentrantLock();

    // 打印次数
    int count;
    // 控制哪个线程执行
    int state = 0;

    public PrintInOrder(int count) {
        this.count = count;
    }

    public void printLetter(String value,int flag) {
        for (int i = 0; i < count; ) {
            lock.lock();
            try {
                // 当flag = 0时 执行A线程   1执行B线程    2执行C线程  3执行D线程,D线程用于换行
                if (state % 4 == flag) {
                    i++;   // i++ 必须加锁  否则无法保证顺序
                    state++;
                    System.out.print(value);
                }
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        PrintInOrder pio = new PrintInOrder(3);

        new Thread(() -> {
            pio.printLetter(Thread.currentThread().getName(),0);
        }, "A").start();

        new Thread(() -> {
            pio.printLetter(Thread.currentThread().getName(),1);
        }, "B").start();

        new Thread(() -> {
            pio.printLetter(Thread.currentThread().getName(),2);
        }, "C").start();

        new Thread(() -> {
            pio.printLetter("\n",3);
        }, "D").start();
    }
}
