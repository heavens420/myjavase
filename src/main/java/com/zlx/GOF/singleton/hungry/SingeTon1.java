package com.zlx.GOF.singleton.hungry;

//饿汉式不存在线程安全问题
public class SingeTon1 {
    private SingeTon1(){
        System.out.println("diaoyongle");
    }

    private final static SingeTon1 INSTANCE = new SingeTon1();

    public static SingeTon1 getInstance() {
        return INSTANCE;
    }
}
