package com.zlx.java8features;

import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * lambda相关用法
 */
@Slf4j
public class LambdaTest {
    public static void main(String[] args) {
//        getMinAndMaxAndAvgValue();
//        getMinAndMaxAndAvgValue2();
//        getMaxAndMinAndAvgValue3();
        getMaxAndMinAndAvgValue4();
//        getSum();
//        concatItem();
//        matchItem();
//        findItem();
//        outputItem();
//        randomNums();
    }

    /**
     * 字符串类型数组转 int 类型数组
     */
    public static void convertStringArrayToInt() {
        String[] array = new String[]{"6", "4", "3", "2"};
        int[] ints = Stream.of(array).mapToInt(Integer::parseInt).toArray();
        System.out.println(Arrays.toString(ints));
    }

    /**
     * 获取数组的最大 最小 平均值
     * 情况1 ： 字符串类型数组
     */
    public static void getMinAndMaxAndAvgValue() {
        String[] array = new String[]{"6", "4", "3", "2"};
        int max = Stream.of(array).mapToInt(Integer::parseInt).max().getAsInt();
        int min = Stream.of(array).mapToInt(Integer::parseInt).min().getAsInt();
        Double avg = Stream.of(array).mapToInt(Integer::parseInt).average().getAsDouble();
        log.info("平均值：{}", avg);
        log.info("最大值：{}", max);
        log.info("最小值：{}", min);
    }

    /**
     * 获取数组中的最大 最小 平均值
     * 情况2：int类型数组
     */
    public static void getMinAndMaxAndAvgValue2() {
        int[] array = new int[]{2, 3, 4, 5, 12, 4, 34};
        int max = IntStream.of(array).max().getAsInt();
        int min = IntStream.of(array).min().getAsInt();
        Double avg = IntStream.of(array).average().getAsDouble();
        log.info("平均值：{}", avg);
        log.info("最大值：{}", max);
        log.info("最小值：{}", min);
    }

    /**
     * 最大 最小 平均值
     */
    public static void getMaxAndMinAndAvgValue3() {
        String[] array = new String[]{"67", "54", "212", "23"};
        IntSummaryStatistics collect = Stream.of(array).collect(Collectors.summarizingInt(Integer::parseInt));
        int max = collect.getMax();
        int min = collect.getMin();
        Double avg = collect.getAverage();
        log.info("平均值：{}", avg);
        log.info("最大值：{}", max);
        log.info("最小值：{}", min);
    }

    /**
     * reduce 求和  最大 最小 平均值
     */
    public static void getSum() {
        String[] array = new String[]{"67", "54", "212", "23"};
        int[] array2 = new int[]{2, 3, 4, 5, 12, 4, 34};

        Integer sum = Stream.of(array).map(Integer::parseInt).reduce(0, Integer::sum);
//        Stream.of(array).mapToInt(Integer::parseInt).reduce(0, Integer::sum);
        int sum2 = IntStream.of(array2).reduce(0, Integer::sum);
        int max = IntStream.of(array2).reduce(Integer::max).getAsInt();
        int min = IntStream.of(array2).reduce(Integer::min).getAsInt();

        log.info("sum : {}", sum);
        log.info("sum2 : {}", sum2);
        log.info("max : {}", max);
    }

    /**
     * max min
     */
    public static void getMaxAndMinAndAvgValue4(){
        String[] array = new String[]{"33", "12", "54", "43", "23"};
        val max = Arrays.stream(array).map(Integer::parseInt).max(Integer::compare).get();
        val min = Arrays.stream(array).map(Integer::parseInt).min(Integer::compare).get();
        System.out.println(max);
        System.out.println(min);
    }

    /**
     * 连接元素
     */
    public static void concatItem() {
        String[] array = new String[]{"33", "12", "54", "43", "23"};
        String reduce = Stream.of(array).reduce((a, b) -> a + "-" + b).get();
        String reduce2 = Stream.of(array).reduce("nihaoaprefix-", String::concat);

        log.info("结果: {}", reduce);
        log.info("结果: {}", reduce2);
    }

    /**
     * 元素是否 满足xx条件
     */
    public static void matchItem() {
        String[] array = new String[]{"33", "12", "54", "43", "23"};

        boolean allMatch = Stream.of(array).allMatch(s -> s.contains("3"));
        boolean anyMatch = Stream.of(array).anyMatch(s -> s.contains("3"));
        boolean noMatch = Stream.of(array).noneMatch(s -> s.contains("3"));

        log.info("所有元素都包含 3 :{}", allMatch);
        log.info("存在元素包含 3 :{}", anyMatch);
        log.info("没有元素包含 3 :{}", noMatch);
    }

    /**
     * 查找元素
     */
    public static void findItem() {
        String[] array = new String[]{"33", "33", "54", "43", "23"};
        String s = Stream.of(array).findFirst().get();
        String s2 = Stream.of(array).findAny().get();
        log.info("first item: {}", s);
        log.info("任意某个 item: {}", s2);
    }

    /**
     * 规则输出元素
     */
    public static void outputItem() {
        Stream.of("fsd-sdf-fsdf-fdsf-sdfs-dfsd-fs-df-sf-sd-fsd-fsd-f-sdf-sd-f", "pdsf-dsf-dsf-sdf-sd-f-dsf")
                .flatMap(s -> Stream.of(s.split("-")))
                .forEachOrdered(System.out::print);
    }

    /**
     * 无限流 生成方式
     */
    public static void randomNums() {
        // 计算阶乘
        val collect = Stream.iterate(2L, x -> x + x).limit(64).collect(Collectors.toList());
//        collect.forEach(System.out::println);

        // 生成随机数
        Stream.generate(() -> Math.random()*10 + 1).forEach(System.out::println);
//        Stream.generate(() -> Math.random()*10 + 1)

    }


}
