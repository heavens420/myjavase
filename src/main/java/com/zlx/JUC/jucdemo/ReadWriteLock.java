package com.zlx.JUC.jucdemo;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLock {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            new Thread(()->{
                try {
                    put(finalI +"",finalI);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }

        for (int i = 0; i < 5; i++) {
            int finalI = i;
            new Thread(() -> {
                try {
                    get(finalI +"");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }

    private volatile static Map<String, Object> map = new HashMap<>(8);

    // 引入读写锁
    private static ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

    public static void put(String key,Object value) throws InterruptedException {
        // 加写锁
        rwLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getId()+"写入"+key+"开始");
            TimeUnit.MICROSECONDS.sleep(300);
            map.put(key,value);
            System.out.println(Thread.currentThread().getId()+"写入"+key+"完成");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            // 释放写锁
            rwLock.writeLock().unlock();
        }

    }

    public static void get(String key) throws InterruptedException {

        rwLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getId()+"读取"+key+"开始");
            TimeUnit.MICROSECONDS.sleep(300);
            map.get(key);
            System.out.println(Thread.currentThread().getId()+"读取结束"+key);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            rwLock.readLock().unlock();
        }

    }
}
