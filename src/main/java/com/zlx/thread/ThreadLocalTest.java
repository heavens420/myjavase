package com.zlx.thread;

public class ThreadLocalTest {
    public static void main(String[] args) {
        ThreadLocal<String> threadLocal = new ThreadLocal();
//        threadLocal.set("hahahah");
//        threadLocal.set("nianai");
//        final String s = threadLocal.get();
//        System.out.println(s);


        new Thread(()->{
            threadLocal.set(Thread.currentThread().getName()+"1");
            System.out.println(threadLocal.get());
        }).start();

        new Thread(()->{
            threadLocal.set((Thread.currentThread().getName()+"2"));
            System.out.println(threadLocal.get());
        }).start();

        final Object o = threadLocal.get();
        System.out.println(o);

    }
}
