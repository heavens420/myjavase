package com.zlx.javase.string;


import lombok.val;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/**
 * StringJoiner test
 *
 */
public class StringJoinerDemo {
    public static void main(String[] args) {
//        test1();
//        test2();
//        test3();
        test4();

    }

    public static void test1(){
        StringJoiner joiner = new StringJoiner("-", "prefix->", "<-suffix");
        joiner.add("a");
        joiner.add("b");
        joiner.add("c");

        System.out.println(joiner);
    }

    public static void test2(){
        final val join = String.join("-", "a", "b", "c");
        System.out.println(join);
    }

    public static void test3(){
        List<String> list = Arrays.asList("a", "b", "c");
//        String[] array = {"fa","s","f","e"};
//        Integer[] array2 = {1, 2, 34, 45};
//        String.join("--", array);  正常
//        String.join("===", array2);  报错
        String result = String.join("--", list);
        System.out.println(result);
    }

    public static void test4(){
        List<String> list = Arrays.asList("a", "b", "c");

        String result = list.stream().map(x -> x).collect(Collectors.joining(", ","{","}"));
        System.out.println(result);
    }

}
