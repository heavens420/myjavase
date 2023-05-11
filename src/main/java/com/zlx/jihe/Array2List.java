package com.zlx.jihe;

import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 数组转集合
 *  方式1 : asList() 用List类型接收 转化后的集合不能使用add()方法
 *  方式2 : asList() 用ArrayList类型接收 转化后的集合同正常集合
 *  方式3 : Collections.addAll(list,array)
 */
public class Array2List {

    public static void main(String[] args) {
//        array2list1();
//        array2list2();
//        array2list3();
        double a = 25/2.0;  // 12.5
//        double a = 25/2;  // 12
//        System.out.println(a);

        orderListByList();
    }


    public static void array2list1(){
        Integer[] array = new Integer[]{1,2,3,4};
//        int[] array = new int[]{1,2,3,4};

        // 数组必须是 对象类型的  不能是8大基本数据类型
        final List<Integer> ints = Arrays.asList(array);

        // 此方式 不能添加，清空，移除 元素 否则报错
        // 但可以修改 查询等
        ints.add(23);
    }

    public static void array2list2(){
        Integer[] array = new Integer[]{1, 2, 3, 4, 5};
        ArrayList arrayList = new ArrayList<Integer>(Arrays.asList(array));

    }

    public static void array2list3(){
        Integer[] array = new Integer[]{1, 2, 4, 5};
        ArrayList arrayList = new ArrayList<Integer>(array.length);
        Collections.addAll(arrayList, array);
        arrayList.forEach(System.out::println);
    }

    public static void orderListByList() {
        List<String> list = new ArrayList<>(Arrays.asList("a12", "p9", "b34", "d3"));
        List<String> orderRule = new ArrayList<>(Arrays.asList("b34", "p9", "a12", "d3"));

        list.sort(((o1, o2) -> {
            int io1 = orderRule.indexOf(o1);
            int io2 = orderRule.indexOf(o2);
            if (io1 != -1) {
                io1 = list.size() - io1;
            }
            if (io2 != -1) {
                io2 = list.size() - io2;
            }
            return  io2 - io1;
        }));

        System.out.println(list);
    }
}
