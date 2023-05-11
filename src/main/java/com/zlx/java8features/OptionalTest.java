package com.zlx.java8features;

import java.util.*;

public class OptionalTest {
    public static void main(String[] args) {
        test1();
    }

    public static void test1() {
        List<String> list = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        User user = new User(12,"sf");
        Optional.ofNullable(list).ifPresent(
               x -> {
                   System.out.println("----");
               }
        );

//        Optional.ofNullable(user).ifPresent(stu -> System.out.println(stu.getName()));

    }
}
