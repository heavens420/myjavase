package com.zlx.java8features;


import lombok.val;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * java8内置 四大lambda接口
 * 消费型
 * 供给型
 * 函数型
 * 断言型
 */
public class LambdaFunction {
    public static void main(String[] args) {
//        conusmer(10 + "", s -> System.out.println("money is :" + s));


//        aupplier(10,() -> ((int) (Math.random() * 10))).forEach(System.out::println);

//        System.out.println(function("你好啊", s -> s.length() + ""));
//        System.out.println(function("你好啊", s -> s + "hahah"));
        predicate(new ArrayList<String>(Arrays.asList("fe","fsfsd","werewr","sfwerwe")),s -> s.length() > 3).forEach(System.out::println);
    }

    /**
     * 消费型接口 : 简单字符串拼接
     *
     * @param money
     * @param consumer
     *
     * Consumer<T> T:传入类型
     */
    public static void conusmer(String money, Consumer<String> consumer) {
        consumer.accept(money);
    }


    /**
     * 供给型接口 ：生成指定数量的随机数
     *
     * @param number
     * @param supplier
     * @return
     *
     * Supplier<T>  T:传入类型
     */
    public static List<Integer> aupplier(int number, Supplier<Integer> supplier) {
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < number; i++) {
            val integer = supplier.get();
            list.add(integer);
        }
        return list;
    }

    /**
     * 函数型接口 ： 字符串简单转换
     *
     * @param str
     * @param function
     * @return
     *
     * Function<T, R> T:传入类型，R:转换后类型
     */
    public static String function(String str, Function<String, String> function) {
        return function.apply(str);
    }


    /**
     * 断言型接口 ：元素过滤
     * @param list
     * @param stringPredicate
     * @return
     *
     * Predicate<T> T: 接收参数类型
     */
    public static List<String> predicate(List<String> list, Predicate<String> stringPredicate) {
        List<String> stringList = new ArrayList<>();
        for (String s : list) {
            if (stringPredicate.test(s)) {
                stringList.add(s);
            }
        }
        return stringList;
    }
}
