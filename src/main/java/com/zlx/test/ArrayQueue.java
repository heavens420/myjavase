package com.zlx.test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static java.lang.Thread.sleep;

public class ArrayQueue {
    public static void main(String[] args) {
        produce();
        customer();
    }
    private static BlockingQueue<Integer> queue = new ArrayBlockingQueue(5);

    public static void produce(){
        for (int i = 0; i < 3; i++) {
            new Thread(()->{
                while (true) {
                    try {
                        sleep((long)Math.random()*1000);
                        System.out.println(Thread.currentThread().getName()+"准备存数据");
                        queue.put(1);
                        System.out.println(Thread.currentThread().getName()+"存放了数据，目前队列里共有："+queue.size()+"个数据");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    public static void customer(){
            while (true){
                new Thread(()->{
                    try {
                        sleep((long) Math.random() * 600);
                        System.out.println(Thread.currentThread().getName()+"准备取数据");
                        queue.take();
                        System.out.println(Thread.currentThread().getName()+"取出了数据，目前队列里有："+queue.size()+"个数据");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
    }
}
