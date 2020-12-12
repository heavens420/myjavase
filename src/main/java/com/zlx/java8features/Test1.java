package com.zlx.java8features;

import java.util.Optional;
import java.util.stream.Stream;

public class Test1 {
    public static void main(String[] args) {
        String temp = "ss";
        Stream.of("a-b-c-d","ni-hao-a").flatMap(s -> Stream.of(s.split("-"))).forEach(System.out::print);
        System.out.println();
        Stream.of("a-b-c-d","wo-hen-hao").map(s -> Stream.of(s.split("-"))).forEach(System.out::print);
        System.out.println();
        Optional.ofNullable(temp).ifPresent(System.out::println);

        System.out.println("-----------------------------------");
        //ofNullable()方法，为空时 创建返回空对象
        Optional.ofNullable("null").ifPresent(System.out::println);
        System.out.println("++++++++++++++++++++++++++++");
        // of()方法，为空时空指针异常
        Optional.of(null).ifPresent(System.out::println);
    }
}
