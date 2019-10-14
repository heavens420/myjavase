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


        //获取CPU逻辑核心（线程数）
        System.out.println(Runtime.getRuntime().availableProcessors());

        long maxMemory = Runtime.getRuntime().maxMemory();
        long totalMemory = Runtime.getRuntime().totalMemory();
        System.out.println("Xmx:"+maxMemory/(double)1024/1024+"MB");
        System.out.println("Xms:"+totalMemory/(double)1024/1024+"MB");
    }
}

