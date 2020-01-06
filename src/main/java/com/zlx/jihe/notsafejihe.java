package com.zlx.jihe;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class notsafejihe {
    //解决Arraylist 线程不安全方案 HashSet 也同理

    public static void main(String[] args) {
        //方案一：vector
        List<Integer> list =  new Vector<Integer>();
        //方案二：collections工具类的 synchronizedList（）方法
        List list1 = Collections.synchronizedList(new ArrayList<Integer>());
        //方案三：juc包下的 CopyOnWriteArrayList<>()方法
        List list2 = new CopyOnWriteArrayList<Integer>();

        //HashSet 线程安全方案
        //set的底层是HashMap value值是常量 key值是 hashset的值
        Set set = new CopyOnWriteArraySet();
        Set set1 = Collections.synchronizedSet(new HashSet<>());

        String s = null;
        try {
        System.out.println(s.length());
        }catch (Exception e){
            System.out.println("err");
        }
//        new Thread(()->{
//            System.out.println("hello");
//        },"aa").start();

    }
}
