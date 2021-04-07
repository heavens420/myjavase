package com.zlx.JUC.jucdemo;


/**
 *  两个线程 同时访问一个对象的两个同步方法是否线程安全
 *          结论：安全
 *
 *          拓展： 若其中一个方法为普通方法，则线程不安全
 */
public class SynchronizedTest {

    public synchronized void test1(){
        for (int i = 0; i < 10; i++) {
            if (i == 8){
                throw new RuntimeException("遇到异常释放锁，下一个线程拿个锁");
            }
            System.out.println(i+"--"+Thread.currentThread().getName());
        }
    }

    public synchronized void test2(){
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) {

        SynchronizedTest synchronizedTest = new SynchronizedTest();

        new Thread(() -> {
            synchronizedTest.test1();
        }).start();

        new Thread(() ->{
            synchronizedTest.test2();
        }).start();
    }
}
