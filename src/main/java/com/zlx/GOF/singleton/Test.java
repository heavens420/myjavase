package com.zlx.GOF.singleton;

import com.zlx.GOF.singleton.lazy.Single1;
import com.zlx.GOF.singleton.hungry.SingeTon1;
import com.zlx.GOF.singleton.hungry.SingleTon2;
import com.zlx.GOF.singleton.lazy.Single3;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Test {
    public static void main(String[] args)  {
//        HCompareSinge2();
//        LCompareSinge1();
//        LCompareSingle2();
        LCompareSingle3();

    }


    public static void HCompareSingleTon1(){
        SingeTon1 instance = SingeTon1.getInstance();
        SingeTon1 instance2 = SingeTon1.getInstance();
        System.out.println(instance == instance2);
    }

    public static void HCompareSinge2(){
        SingleTon2 instance = SingleTon2.INSTANCE;
        SingleTon2 singleTon2 = SingleTon2.INSTANCE;
        System.out.println(instance == singleTon2);
    }

    public static void LCompareSinge1(){
        Single1 singleTon = Single1.getSingleTon();
        Single1 singleTon1 = Single1.getSingleTon();
        System.out.println(singleTon == singleTon1);
    }

    public static void LCompareSingle2(){
        Set set = new HashSet();
        for (int i = 0; i < 200000; i++) {
            int finalI = i;
            new Thread(() -> {
                try {
                    TimeUnit.MICROSECONDS.sleep(10);
                    Single1 singleTon = Single1.getSingleTon();
                    set.add(singleTon.hashCode());
//                System.out.println(Thread.currentThread().getName()+finalI);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },i+"线程").start();
        }
        set.forEach(System.out::println);
    }

    public static void LCompareSingle3(){
        Single3 instance = Single3.getInstance();
        Single3 instance2 = Single3.getInstance();
        System.out.println(instance == instance2);
    }

}
