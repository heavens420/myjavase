package com.zlx.JUC.jucdemo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock的简单使用
 */
public class SaleTickets {
    private int Tickets = 100;

    Lock lock = new ReentrantLock();
    public void sale(){
        lock.lock();
        try {
            if (Tickets > 0){
                System.out.println(Thread.currentThread().getName()+"\t卖出了第："+Tickets-- +"张票 \t剩余票数："+Tickets);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        SaleTickets saleTickets = new SaleTickets();

        new Thread(()->{
            for (int i = 0; i < 100; i++) {
                saleTickets.sale();
            }
        },"A").start();
        new Thread(()->{
            for (int i = 0; i < 100; i++) {
                saleTickets.sale();
            }
        },"B").start();
        new Thread(()->{
            for (int i = 0; i < 100; i++) {
                saleTickets.sale();
            }
        },"C").start();
    }
}
