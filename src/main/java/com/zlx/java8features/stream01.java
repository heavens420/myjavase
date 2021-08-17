package com.zlx.java8features;


import java.util.*;
import java.util.stream.Stream;

/**
 * Java 8 新特性
 *
 */
public class stream01 implements RandomAccess {
    public static void main(String[] args) {
        //Optionals : 防止空指针异常

        //为非null的值创建一个Optional
        Optional<String> optional = Optional.of("sdf");
        //判断optional是否为null 非空为 true 空为false
        boolean isEmpty = optional.isPresent();
        //获取optional的值 无值时 抛出 NoSuchEelmentException
        String value = optional.get();
        //如果有值 返回该值 否则返回自定义的值
        String value2 = optional.orElse("defaultValue");
        //如果有值 为其调用 consumer 否则不做处理
        optional.ifPresent(s -> System.out.println(s.charAt(1)));

        System.out.println("是否为空:"+isEmpty+"\n值为:"+value+"\t");

        //stream():表示能应用在一组元素上一次执行的操作序列  分为中间操作(返回的是stream本身)和最终操作 (返回值是特定的计算结果)

        //此处 List<String> 要加泛型  否则 下面filter 无法调用 startWith()等方法
        List<String> stringList = new ArrayList(Arrays.asList("Da","g","d","Fa","fsf","fs"));
        //过滤以 'f'开头的字符串
        stringList.stream().filter((s) -> s.startsWith("f")).forEach(System.out::println);
        //过滤以'f'开头的字符串并将其排序
        stringList.stream().sorted().filter(s -> s.startsWith("f")).forEach(System.out::println);

        //map()映射
        stringList.stream().map(String::toUpperCase).filter(s -> s.length() >= 2).forEach(System.out::println);

        //以上均为 中间操作
        //一下均为 最终操作

        //match() 匹配

        //匹配任意一个就返回true
        boolean anyMatch = stringList.stream().anyMatch(s -> s.startsWith("f"));
        //全匹配返回true 否则返回false
        boolean allMatch = stringList.stream().allMatch(s -> s.startsWith("f"));
        //全不匹配 返回true 否则返回false
        boolean noneMatch = stringList.stream().noneMatch(s -> s.startsWith("f"));
        System.out.println("任意匹配一个:"+anyMatch+
                            "\n全部匹配:"+allMatch+
                            "\n无一匹配:"+noneMatch);

        //count() 返回stream()中的元素的个数 返回值类型 long
        long count1 = stringList.stream().count();
        long count2 = stringList.stream().filter(s -> s.startsWith("f")).count();
        System.out.println("总个数:"+count1+"\t"+"以f开头的元素数量:"+count2);

        //reduce() 规约 : 将stream中的多个元素规约成一个元素 规约结果是通过OPtional接口表示的
        Optional<String> reduce1 = stringList.stream().reduce((s1,s2) -> s1+"-"+s2);
        reduce1.ifPresent(System.out::println);

        //字符串连接x`
        String con = Stream.of("s","gg","erwr","fa").reduce("",String::concat);
        //求最小值
//        double minValue = Stream.of(1,3,5,-3,-5.3,-43,-43.23,5.43).reduce(Double.MAX_VALUE,Integer::min);
        //求和 有起始值
        double sum = Stream.of(1,2,3,4,5,6,7,8,9,0).reduce(0,Integer::sum);
        //求和 无起始值
        int sum2 = Stream.of(1,2,3,4,5,6,7,8,9,10).reduce(Integer::sum).get();

        System.out.println(con+"\t\n"+sum+"\t\n"+sum2);
    }
}
