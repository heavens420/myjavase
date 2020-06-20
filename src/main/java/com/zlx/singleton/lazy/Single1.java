package com.zlx.singleton.lazy;

public class Single1 {
    private Single1(){}

    private static Single1 singleTon = null;

    public static Single1 getSingleTon() {
        synchronized (Single1.class){
            if (singleTon == null){
                singleTon = new Single1();
                return singleTon;
            }
            return null;
        }
    }
}
