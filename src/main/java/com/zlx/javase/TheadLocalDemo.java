package com.zlx.javase;

import java.util.Random;


/**
 * TheadLocal 与 synchronized的区别：
 *  threadLocal会将主内存的值拷贝一个副本给每一个本地线程 各个线程的值互不影响相互独立 同时副本值不会同步回主内存，他们操作的是不同的值
 *  加锁 会将主线程的值复制给其他线程，但是其他线程修改值后会同步回主内存，他们始终在操作同一个值
 *
 *
 *  InheritableThreadLocal 使子线程可以访问父线程
 */
public class TheadLocalDemo {

    public static void main(String[] args) {
        Demo demo = new Demo();
        Thread t1 = new Thread(demo);
        Thread t2 = new Thread(demo);
        t1.start();
        t2.start();
    }

    public static class Demo  implements Runnable{
        private String str = "as";

        ThreadLocal threadLocal = new ThreadLocal<String>();

        @Override
        public void run() {
            threadLocal.set(str+ new Random().nextInt(100)+20);

            System.out.println(threadLocal.get());
        }
    }
}
