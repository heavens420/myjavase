package com.zlx.singleton;

import com.zlx.singleton.hungry.SingeTon1;
import com.zlx.singleton.hungry.SingleTon2;
import com.zlx.singleton.lazy.Single1;

public class Test {
    public static void main(String[] args) {
//        HCompareSinge2();
        LCompareSinge1();
    }


    public static void HCompareSingleTon1(){
        SingeTon1 instance = SingeTon1.getINSTANCE();
        SingeTon1 instance2 = SingeTon1.getINSTANCE();
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
}
