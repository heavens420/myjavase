package com.zlx.JUC;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class synchronousqueue {
    public static void main(String[] args) throws InterruptedException {
        produce();
        Thread.sleep(1000);
        consumer();
    }

    final static BlockingQueue<Integer> synqueue = new SynchronousQueue(true);
    public static void produce(){
        for (int i = 0;i<2; i++) {
            new Thread(()->{
                System.out.println("生产线程开始");
                try {
                        synqueue.put(3);
                    } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("生产线程结束");
            }).start();
        }
    }

    public static void consumer(){
        for (int i = 0; i<2; i++) {
            new Thread(()->{
                System.out.println("消费线程开始");
                try {
                    System.out.println(synqueue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("消费线程结束");
            }).start();
        }
    }
}
