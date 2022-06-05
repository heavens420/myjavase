package com.zlx.GOF.singleton.hungry;

/**
 * 静态内部类
 */
public class StaticInnerClass {
    private static volatile StaticInnerClass singleton = null;

    public StaticInnerClass() {
        System.out.println(Thread.currentThread().getName()+"构造方法");
    }

    public static StaticInnerClass getInstance(){
        if (singleton == null) {
            singleton = new StaticInnerClass();
        }
        return singleton;
    }
}
