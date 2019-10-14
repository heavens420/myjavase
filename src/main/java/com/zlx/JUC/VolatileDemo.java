package com.zlx.JUC;

import java.util.concurrent.TimeUnit;

/**
 * volatile 保证了 数据的可见性 禁止指令重排  但不保证原子性
 */
public class VolatileDemo {
    public static void main(String[] args) {
        BC bc = new BC();

        new Thread(()->{
            try {
                //等待两秒 保证 main线程执行
                TimeUnit.SECONDS.sleep(2);
                bc.changeNumber();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        while (bc.number == 1){
            //当number 改变时 不执行  即其它线程操作资源可见 此循环不执行
        }

        System.out.println("main running ");
    }
}

class BC{
    volatile int number = 1; //volatile 当其它线程改变其值时 通过某种机制通知另外的其它线程  保证了可见性
    public void changeNumber(){
        number = 99;
    }
}
