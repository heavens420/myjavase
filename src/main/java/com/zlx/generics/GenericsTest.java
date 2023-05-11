package com.zlx.generics;


import java.util.*;

public class GenericsTest {
    public static void main(String[] args) {
        ArrayDeque<Map<String, Object>> maps = new ArrayDeque<>();
        AbstractCollection<Map<String,Object>> que = new ArrayDeque<>();


    }

    public static void test1() {
        List<Number> numberList = new ArrayList<Number>();
        numberList.add(1);

        // 编译不通过
//        List<Number> numbers = new ArrayList<Integer>();

        // 编译通过  ？extends T 表示协变 => 表示Integer 是 Number的子类
        List<? extends Number> numbers = new ArrayList<Integer>();
        // 协变无法添加元素 只能获取元素 故下行代码编译报错
//        numbers.add(1);

        // 使用逆变可以实现添加元素 ？super T 表示逆变 => Number 是 Integer的父类
        List<? super Integer> numbers2 = new ArrayList<Number>();
        numbers2.add(1);
        // 逆变获取元素的类型并非添加元素时的类型 而是Object类型 故下行代码报错
//        Integer a =numbers2.get(0);
        // 编译通过
        Object object = numbers2.get(0);

        // 数组协变
        // 下行代码正常编译 但运行异常
        Number[] numderArray = new Integer[10];
        numderArray[0] = 1.0;
    }
}
