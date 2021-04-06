package com.zlx.JUC.jucdemo;

/**
 * 传统加锁方法实现交替打印
 */
public class PrintInOrder2 {

    public int count = 0;
    public int state = 0;
    public static Object object = new Object();  // 用于加类锁

    public PrintInOrder2(int count) {
        this.count = count;
    }

    public void printInOrder(String vale, int flag) throws InterruptedException {
        for (int i = 0; i < count; i++) {
            synchronized (object) {
                // 此处必须用while 而不能用if,while类似自旋,不满足条件则一直重试,直到满足条件,执行线程.
                // 如果是if 没有自旋,第一次不满足则不再判断,从而使程序继续执行,打印value使得顺序无法保证
                while (state % 4 != flag) {
                    object.wait();
                }
                state++;
                System.out.print(vale);

                /**
                 * 此处是否可以使用 notify() ?
                 *      不可以.
                 *      因为notify()只随机唤醒一个正在等待的线程,如果他唤醒的还是自己,自己不符合条件所以阻塞,导致死锁.
                 *      而notifyAll() 唤醒所有等待的线程,即便唤醒了自己,自己继续阻塞,总会有一个线程满足state % 4 != flag,不会出现死锁
                 */
                object.notifyAll();
            }
        }
    }

    public static void main(String[] args) {
        PrintInOrder2 pio = new PrintInOrder2(30);

        new Thread(() -> {
            try {
                pio.printInOrder(Thread.currentThread().getName(),0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"A").start();

        new Thread(() -> {
            try {
                pio.printInOrder(Thread.currentThread().getName(),1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"B").start();

        new Thread(() -> {
            try {
                pio.printInOrder(Thread.currentThread().getName(),2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"C").start();

        new Thread(() -> {
            try {
                pio.printInOrder("\n",3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"D").start();

    }
}
