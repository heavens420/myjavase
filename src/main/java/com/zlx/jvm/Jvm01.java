package com.zlx.jvm;

/**
 * 查看 jdk自带类和 自己创建的类的加载器
 *
 */
public class Jvm01 {
    public static void main(String[] args) {
        Object object = new Object();
        Jvm01 jvm01 = new Jvm01();
        System.out.println(object.getClass().getClassLoader());

        System.out.println("------------------------");

        System.out.println(jvm01.getClass().getClassLoader().getParent().getParent());
        System.out.println(jvm01.getClass().getClassLoader().getParent());
        System.out.println(jvm01.getClass().getClassLoader());
    }
}

