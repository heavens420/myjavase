package com.zlx.GOF.iterator;

import java.util.Iterator;

public class Client {
    public static void main(String[] args) {
        // 可迭代对象
        MyIterable myIterable = new MyIterable();
        myIterable.add(new Student("4","zhaoliu"));
        // 获取迭代器
        Iterator<Student> iterator = myIterable.iterator();

        while (iterator.hasNext()) {
            Student student = iterator.next();
            System.out.println(student);
        }
    }
}
