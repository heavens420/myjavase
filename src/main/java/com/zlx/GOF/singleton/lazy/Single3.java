package com.zlx.GOF.singleton.lazy;


/**
 * @Author Zhao LongLong
 * @Date 2020/9/12
 * @Version 1.0
 * @Desc
 */
public class Single3 {


    private Single3(){}

    private static class SingleInstance{
        private static final Single3 single3 = new Single3();
    }

    public static Single3 getInstance(){
        return SingleInstance.single3;
    }
}
