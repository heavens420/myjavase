package com.zlx.java8features;

import java.util.function.*;

/**
 * java8 Function {@link java.util.function}
 */
public class LambdaFunction2 {

    public static void main(String[] args) {

        // 1.  Consumer<T>：接受一个输入参数并且无返回的操作。
        Consumer<Integer> print = System.out::println;
        print.accept(9); // 输出 9

        // 2.  Supplier<T>：产生一个给定类型的结果。
        Supplier<String> supplyString = () -> "hello world";
        System.out.println(supplyString.get()); // 输出 hello world

        // 3.  Predicate<T>：接受一个参数返回一个Boolean值的操作。
        Predicate<Integer> isGreaterThanZero = n -> n > 0;
        System.out.println(isGreaterThanZero.test(-1)); // 输出 false

        // 4.  Function<T,  R>：接受一个输入参数，返回一个结果。
        Function<String, Integer> strLength = String::length;
        System.out.println(strLength.apply("hello")); // 输出 5

        // 5.  UnaryOperator<T>：和Function接口相同，但输入和输出类型相同。
        UnaryOperator<Integer> addOne = x -> x + 1;
        System.out.println(addOne.apply(1)); // 输出 2

        // 6.  BinaryOperator<T>：和Function接口相同，但输入和输出类型相同。
        BinaryOperator<Integer> addTwo = (x, y) -> x + y + 2;
        System.out.println(addTwo.apply(1, 2)); // 输出 5

        // 7.  BiPredicate<T,  U>：接受两个参数返回一个Boolean值的操作。
        BiPredicate<Integer, Integer> isEqual = Integer::equals;
        System.out.println(isEqual.test(1, 1)); // 输出 true

        // 8.  BiFunction<T,  U,  R>：接受两个输入参数并返回一个结果。
        BiFunction<Integer, Integer, Integer> sum = Integer::sum;
        System.out.println(sum.apply(1, 2)); // 输出 3

        // 9.  BiConsumer接口：表示接受两个参数的不返回结果的操作。
        BiConsumer<Integer, String> biConsumer = (num, str) -> System.out.println(num + str);
        biConsumer.accept(123, "456");

        // 10.  IntFunction、LongFunction、DoubleFunction接口：表示接受一个整型、长整型或双精度浮点数字参数并返回一个结果的操作。
        IntFunction<String> intFunction = num -> "数字" + num;
        System.out.println(intFunction.apply(123));

        // 11.  ToIntFunction、ToLongFunction、ToDoubleFunction接口：表示将一个对象转换为整型、长整型或双精度浮点数的操作。
        ToIntFunction<String> toIntFunction = Integer::parseInt;
        System.out.println(toIntFunction.applyAsInt("123"));

        // 12.  ObjIntConsumer、ObjLongConsumer、ObjDoubleConsumer接口：表示接受一个对象和一个整型、长整型或双精度浮点数字参数的不返回结果的操作。
        ObjIntConsumer<String> objIntConsumer = (str, num) -> System.out.println(str + num);
        objIntConsumer.accept("数字", 123);

        // 13.  IntConsumer、LongConsumer、DoubleConsumer接口：表示接受一个整型、长整型或双精度浮点数字参数的不返回结果的操作。
        IntConsumer intConsumer = num -> System.out.println("数字" + num);
        intConsumer.accept(123);

    }
}
