package com.zlx.JUC;

public class ThreadCreate {
    public static void main(String[] args) {
        createThread();
    }
    public static void  createThread(){
        Runnable r = ()->{
            for (int i = 0; i < 500; i++) {
                System.out.println(Thread.currentThread().getName()+" "+i);
            }
        };

        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 500; i++) {
                    System.out.println();
                }
            }
        };
        Thread t1 = new Thread(r,"t1");
        Thread t2 = new Thread(r,"t2");
        t1.setPriority(10);
        t2.setPriority(2);
        t1.start();
        t2.start();
    }
}
