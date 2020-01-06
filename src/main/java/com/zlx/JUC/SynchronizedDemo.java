package com.zlx.JUC;

/**
 * 1 synchronized在方法上加锁 实际上锁的是当前对象 同一时刻 只要有一个线程调用了其中一个synchronized方法 其它线程就只能等待
 *      也就是说 某一时刻只能有一个线程去访问者些synchronized方法
 * 2 普通方法与synchronized方法无关 不与同步方法竞争 不会延迟4s打印
 *
 * 3 若对方法加 static 和 synchronized 则锁的是整个class 不论创建几个该对象 都被锁
 */

import java.util.concurrent.TimeUnit;

public class SynchronizedDemo {
    public static void main(String[] args) throws InterruptedException {
        Phone phone = new Phone();
        Phone phone1 = new Phone();

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(0);
                phone.sendEmail();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();


//        TimeUnit.SECONDS.sleep(1);


        //new Thread(phone::sendMsm).start();

        new Thread(() -> phone.say()).start();

        new Thread(phone1::sendMsm).start();
    }
}

class Phone{

    public /* static*/ synchronized void sendEmail() throws InterruptedException {
        TimeUnit.SECONDS.sleep(4);
        System.out.println("send email");
    }

    public synchronized void sendMsm() {
        System.out.println("send message");
    }

    public void say(){
        System.out.println("hello world");
    }
}