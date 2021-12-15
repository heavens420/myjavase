package com.zlx.JUC.queue;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 *  queue的基本操作：
 *                抛出异常          特殊值            阻塞            超时
 *     插入：      add(e)          offer(e)        put(e)          offer(e,time,unit)
 *     移除：      remove()        poll()          take()          poll(time,unit)
 *     检查：      element()       peek()             \                \
 *
 */
public class ArrayQueue {
    public static void main(String[] args) {

    }


    public static void test1(){
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(3);
//        插入元素，超出容量会报错
        System.out.println(queue.add("a"));
        System.out.println(queue.add("b"));
        System.out.println(queue.add("c"));

//        检查当前队列里第一个元素
        System.out.println(queue.element());

//        删除元素 元素队列为空会报错
        System.out.println(queue.remove());
        System.out.println(queue.remove());
        System.out.println(queue.remove());
    }

    public static void test2(){
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(3);
        queue.offer("a");
        queue.offer("b");
        queue.offer("c");

        System.out.println(queue.element());
    }
}
