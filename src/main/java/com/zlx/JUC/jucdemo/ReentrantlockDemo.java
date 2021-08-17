package com.zlx.JUC.jucdemo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用 Reentrantlock 精确控制线程的唤醒和休眠
 *
 * 创建三个线程 A B C
 * A 线程打印 5次
 * B 线程打印10 次
 * C 线程打印15 次
 * 重复上述打印 10次 且顺序不乱（A B C依次打印）
 *
 * Reentrantlock 的Condition类可以精确控制 要唤醒哪一个线程
 * 不同与 synchronized 的notify 方法 随机唤醒线程
 */
public class ReentrantlockDemo {
    public static void main(String[] args) {
        Data data = new Data();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                data.print5(5);
            }
        },"A").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                data.print10(10);
            }
        },"B").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                data.print15(15);
            }
        },"C").start();
    }
}

class Data{
    int flag = 1; //标志位 用来控制哪个线程执行
    Lock lock = new ReentrantLock();

    //创建3把相同的钥匙 可以开启同一把锁
    Condition threadA = lock.newCondition();
    Condition threadB = lock.newCondition();
    Condition threadC = lock.newCondition();

    public void print5(int n){
        lock.lock();
        try {
            while (flag != 1){ //flag = 1 时 A线程执行
                threadA.await();
            }
            for (int i = 0; i < n; i++) {
                System.out.println(Thread.currentThread().getName()+" "+i+"\t");
            }
            flag = 2; //改变标志位 用来唤醒B线程
            threadB.signal(); //精确唤醒 B 线程
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void print10(int n){
        lock.lock();
        try {
            while (flag != 2){  //flag = 2 时 线程B执行
                threadB.await();
            }
            for (int i = 0; i < n; i++) {
                System.out.println(Thread.currentThread().getName()+" "+i+"\t");
            }
            flag = 3; //改变标志位 用来唤醒 C线程
            threadC.signal(); //精确唤醒C线程
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void  print15(int n){
        lock.lock();
        try {
            while (flag != 3){
                threadC.await();
            }
            for (int i = 0; i < n; i++) {
                System.out.println(Thread.currentThread().getName()+" "+i+"\t");
            }
            flag = 1; //改变标志位 用于唤醒 A 线程
            threadA.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}