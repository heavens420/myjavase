package com.zlx.thread;

import java.lang.annotation.Target;

/**
 * @Author Zhao LongLong
 * @Date 2020/9/16
 * @Version 1.0
 * @Desc    线程组
 *          每一个线程都必须在线程组里面，
 *          当线程未指定所属线程组时，默认线程组为该线程的父线程
 */
public class T3 {
    public static void main(String[] args) {
        TA();

        System.out.println("执行main方法线程的名字："+Thread.currentThread().getName());
    }

    public static void TA(){
        new Thread(() -> System.out.println(
                "所属线程组名称："+Thread.currentThread().getThreadGroup().getName()+"\n当前线程名称"+
                        Thread.currentThread().getName()),"A").start();
    }

    /**
     * 设置线程优先级
     *      优先级只是给操作系统的建议执行顺序 不能保证优先级越高 一定最先执行
     *      一般来说 优先级默认为5 最大10 最小1 守护线程的优先级低于5
     */
    public static void TB(){
        for (int i = 0; i < 10; i++) {
            switch (i){
                case 1:
                    Thread thread = new Thread(() -> System.out.println(Thread.currentThread().getName()), "线程-" + i);
                    thread.setPriority(1);
                    thread.start();break;
                case 3:
                    Thread thread2 = new Thread(() -> System.out.println(Thread.currentThread().getName()), "线程-" + i);
                    thread2.setPriority(1);
                    thread2.start();break;
                default:

            }
            if (i %2 == 0){
                new Thread(() -> {
                    System.out.println(Thread.currentThread().getName());
                },"线程-"+i).setPriority(8);
            }
        }
    }
}
