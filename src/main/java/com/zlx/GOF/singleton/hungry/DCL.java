package com.zlx.GOF.singleton.hungry;

/**
 * 双重检测锁
 */
public class DCL {
    private  static volatile DCL dcl = null;

    private DCL() {
        System.out.println(Thread.currentThread().getName()+"构造方法");
    }

    public static DCL getInstance(){
        if (dcl == null) {
            synchronized (DCL.class) {
                if (dcl == null) {
                    dcl = new DCL();
                }
            }
        }
        return dcl;
    }
}
