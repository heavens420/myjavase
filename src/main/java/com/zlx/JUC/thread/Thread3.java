package com.zlx.JUC.thread;

import com.zlx.java8features.User;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Thread3 {
    public static void main(String[] args) throws InterruptedException {

        AtomicInteger inCount = new AtomicInteger(0);
        AtomicInteger outCount = new AtomicInteger(0);
        ArrayBlockingQueue<User> users = new ArrayBlockingQueue<User>(100);
        for (int i = 0; i < 700; i++) {
            users.offer(new User());
        }

        for (int i = 0;i < 10;i++) {
            User user = users.remove();
            Thread thread = new Thread(() -> {
                try {
                    new Thread3().test1(user, inCount, outCount);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
//            thread.setPriority(i % 10 + 1);
            thread.start();
            new Thread(() -> new Thread3().test2(user)).start();

        }

//        while (true) {
//            new Thread(() -> new Thread3().test2(user)).start();
//        }

    }


    public void test1(User user,AtomicInteger inCount,AtomicInteger outCount) throws IOException {
        synchronized (user) {
            try {
                System.out.println(LocalDateTime.now()+"--begin waiting...property："+Thread.currentThread().getPriority()+"--"+Thread.currentThread().getName()+"--inCount:"+inCount.incrementAndGet() + "--outCount:"+outCount);
                user.wait();
                System.out.println(LocalDateTime.now()+"------test1------property:"+Thread.currentThread().getPriority()+"--"+Thread.currentThread().getName()+"--inCount:"+inCount + "--outCount:"+outCount.incrementAndGet());
            } catch (InterruptedException e) {
                System.out.println("---");
            }
        }
    }

    public void test2(User user) {
        synchronized (user) {
//            user.notifyAll();
            user.notify();
        }
    }



}
