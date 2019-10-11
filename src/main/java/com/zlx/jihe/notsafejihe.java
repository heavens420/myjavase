package com.zlx.jihe;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class notsafejihe {
    //解决Arraylist 线程不安全方案

    public static void main(String[] args) {
        List<Integer> list =  new Vector<Integer>();
        List list1 = Collections.synchronizedList(new ArrayList<Integer>());
        List list2 = new CopyOnWriteArrayList<Integer>();


        //set的底层是HashMap value值是常量 key值是 hashset的值
        Set set = new CopyOnWriteArraySet();
        Set set1 = Collections.synchronizedSet(new HashSet<>());

//        new Thread(()->{
//            System.out.println("hello");
//        },"aa").start();

    }
}
