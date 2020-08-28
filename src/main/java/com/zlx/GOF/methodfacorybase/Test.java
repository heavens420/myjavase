package com.zlx.GOF.methodfacorybase;

/**
 * @Author Zhao LongLong
 * @Date 2020/8/26
 * @Version 1.0
 */
public class Test {
    public static void main(String[] args) {
        Product1 p1 = new Product1();
        p1.show();

        Product2 p2 = new Product2();
        p2.show();
    }
}
