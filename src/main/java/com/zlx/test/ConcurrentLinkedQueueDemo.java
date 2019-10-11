package com.zlx.test;

import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConcurrentLinkedQueueDemo {
    public static void main(String[] args) {
        int a = 1;
        int tail = 2;
        int b = tail;
        System.out.println(a != (a = tail));
        //System.out.println(b != b);
        ExecutorService es = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 50; i++) {
            es.submit(new Offer());
            es.submit(new Poll());
        }
        es.shutdown();
       // System.out.println(SingletonQueue.queue == SingletonQueue.queue);
    }
}

class Offer implements Runnable{
    ConcurrentLinkedQueue queue = SingletonQueue.queue;
    int s = new Random().nextInt(Integer.MAX_VALUE);
    @Override
    public void run() {
        if (queue.size()==0&queue.isEmpty()){ //推荐使用 isEmpty    size()方法需要遍历 效率低   size()返回的结果不一定精确（因为遍历时没有加锁）
                queue.offer(s);
                System.out.println(s+"入队");
        }
    }
}

class Poll implements Runnable{

    ConcurrentLinkedQueue<Integer> queue = SingletonQueue.queue;
    @Override
    public void run() {
        if (!queue.isEmpty()){
                Integer s = queue.poll();
                System.out.println("出队数据"+s);
        }
    }
}

class SingletonQueue{
    public static ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue<>();
    private SingletonQueue(){}

}