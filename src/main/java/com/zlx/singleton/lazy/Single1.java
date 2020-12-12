package com.zlx.singleton.lazy;

public class Single1 {
    private Single1(){}

    private volatile static Single1 singleTon = null;

    public static Single1 getSingleTon() {
        if (singleTon == null) {
            synchronized (Single1.class){
                // 双重检测锁也不一定绝对安全，当一个线程 已经创建对象后，另外的线程对该线程资源不可见，
                // 在该线程将创建的对象同步到堆之前，其它线程可能仍然创建对象，故可使用volatile关键字 禁止指令重排以及保证线程资源的可见性
                if (singleTon == null){
                    singleTon = new Single1();
                }
            }
        }
        return singleTon;
    }
}
