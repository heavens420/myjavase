package com.zlx.GOF.prototype.base;

/**
 * @Author Zhao LongLong
 * @Date 2020/8/25
 * @Version 1.0
 */
public class Test {
    public static void main(String[] args) throws CloneNotSupportedException {
        RealizeType obj1 = new RealizeType();
        RealizeType obj2 = ((RealizeType) obj1.clone());

        System.out.println(obj1 == obj2);
    }
}
