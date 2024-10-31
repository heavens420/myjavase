package com.zlx.JUC.thread;

import java.util.LinkedList;
import java.util.Queue;

public class ProducerConsumerExample {
    private final Queue<Integer> queue = new LinkedList<>();
    private final int MAX_SIZE = 10;

    // 生产者线程
    class Producer implements Runnable {
        @Override
        public void run() {
            while (true) {
                synchronized (queue) {
                    if (queue.size() == MAX_SIZE) {
                        System.out.println("Queue is full, producer waiting...");
                        try {
                            queue.wait();
                            System.out.println("waiting... consumer "+Thread.currentThread().getName());
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }

                    queue.add(new Integer(1));
                    System.out.println("Produced: " + queue.peek());

                    // 唤醒所有等待的消费者线程
//                    queue.notifyAll();
                }
            }
        }
    }

    // 消费者线程
    class Consumer implements Runnable {
        @Override
        public void run() {
            while (true) {
                synchronized (queue) {
                    if (queue.isEmpty()) {
                        System.out.println("Queue is empty, consumer waiting...");
                        try {
                            queue.wait();
                            System.out.println("wating producer thread"+Thread.currentThread().getName());
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }

                    queue.remove();
                    System.out.println("Consumed: " + queue.peek());

                    // 唤醒所有等待的生产者线程
                    queue.notifyAll();
                }
            }
        }
    }

    public static void main(String[] args) {
        ProducerConsumerExample example = new ProducerConsumerExample();
        Thread producerThread = new Thread(example.new Producer());
        Thread consumerThread = new Thread(example.new Consumer());

        producerThread.start();
        consumerThread.start();
    }
}

