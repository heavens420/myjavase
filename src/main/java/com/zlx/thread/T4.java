package com.zlx.thread;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * @Author Zhao LongLong
 * @Date 2020/9/16
 * @Version 1.0
 * @Desc    Java规定的线程状态
 *          NEW             线程刚创建状态
 *          RUNNABLE        线程正在运行的状态  runnable状态的线程也可能处于等待其它系统资源状态，比如等待IO
 *          BLOCKED         线程阻塞的状态，此时正在获得锁，还未获得锁
 *          WAIT            线程等待的状态，与阻塞不同的是，等待的线程必须被唤醒才能继续执行(notify,notifyAll)
 *          TIME_WAITING    超时等待状态，过时自动唤醒，如给在A线程前加了sleep A线程的状态为此状态
 *          TERMINATED      线程执行结束
 *
 */
public class T4 {
    public static void main(String[] args) throws InterruptedException {

        Thread ta = TA();
        Thread tb = TB();
        // a b两个线程 在主线程中运行，启动(调用start)之后开始争抢资源，
        ta.start();
        TimeUnit.MICROSECONDS.sleep(10);
        tb.start();

        System.out.println(ta.getName()+"<--name||状态-->"+ta.getState());
        System.out.println(tb.getName()+"<--name||状态-->"+tb.getState());

        System.out.println(Arrays.toString(getThread()));
    }

    public static Thread TA(){
        return new Thread(() -> {
            PrintLine();
        },"A");
    }

    public static Thread TB(){
        return new Thread(() -> {
            PrintLine();
            Thread.interrupted();
        },"B");
    }

    public static synchronized void PrintLine(){
        try {
            TimeUnit.SECONDS.sleep(1);
            System.out.println("wait……");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread();
            threads[i].start();
        }
    }



    /**
     * 获取当前所有线程
     * @return
     */
    public static Thread[] getThread(){
        ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
        while (threadGroup.getParent() != null){
            threadGroup = threadGroup.getParent();
        }
        int i = threadGroup.activeCount();
        Thread[] threads = new Thread[i];

        // 把当前活动的线程复制到指定的数组中去
        threadGroup.enumerate(threads);

        return threads;
    }
}
