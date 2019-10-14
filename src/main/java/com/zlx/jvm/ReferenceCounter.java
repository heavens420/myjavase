package com.zlx.jvm;


/**
 * 用于演示 引用计数算法的 循环引用问题
 */
public class ReferenceCounter {

    private byte[] data = new byte[2*1024*1024];
    Object instance = null;

    public static void main(String[] args) {
        ReferenceCounter objectA = new ReferenceCounter();
        ReferenceCounter objectB = new ReferenceCounter();
        objectA.instance = objectB;
        objectB.instance = objectA;
        objectA = null;
        objectB = null;

        //手动开启GC
        //System.gc();
    }
}
