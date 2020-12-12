package com.zlx.java8features;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class stream02 {
    public static void main(String[] args) {
//        Stream.of(1,3,5,6,7).
//                map(l->l.len);
        Stream.of("14242","343","89").map(s -> s.length()).forEach(System.out::println);

        Stream.of("ufoa","fa","ruwu").map(s -> s.length()).forEach(System.out::println);

        //mapToDoubble mapToLong 等类似
        Stream.of("fsf","fsf").mapToInt(String::length).forEach(System.out::println);

        Stream.of("a-b-c-d","e-f-g").flatMap(s -> Stream.of(s.split("-"))).forEach(System.out::println);

        //限制输出数量
        Stream.of(12,34,65,78,4353,242).limit(2).forEach(System.out::println);

        //去除重复 并排序
        Stream.of(1,2,4,5,2,35,6,1,2).distinct().sorted().forEach(System.out::println);

        Stream.of(1,2,4,5,32,52,23,9).filter(e -> e >= 9).sorted().forEach(System.out::println);

        User u1 = new User(122,"你");
        User u2 = new User(33,"jsj");
        User u3 = new User(3,"jsj");
        User u4 = new User(303,"jsj");

        Stream.of(u1,u2,u3,u4).peek(e -> e.setId(e.getId()+1)).peek(e ->e.setName(e.getName()+"ll")).forEach(System.out::println);

        //跳过前两个数不输出 与limit 对应
        Stream.of(12,34,56).skip(2).forEach(System.out::println);

        //自定义排序规则
        Stream.of(u1,u2,u3,u4).sorted((s,b)->s.getId()>b.getId()?1:s.getId().equals(b.getId())?0:-1).forEach(System.out::println);

        //collect 将元素收集到集合
        List list = Stream.of(12,34,56,34).collect(Collectors.toList());
        list.forEach(System.out::println);

        Stream.of("ni","jf","fs","fs","fr").collect(Collectors.toSet()).forEach(System.out::println);

        System.out.println(Stream.of(12, 34, 65, 765).count());

        //获取第一个元素
        Optional<Integer> s = Stream.of(12, 34, 56, 34, 78, 35, 24).findFirst();
        s.ifPresent(System.out::println);

//        Optional<Integer>(Stream.of(1,2,4,5).findFirst()).ifPresent(System.out::println);

        //随机获取任意一个元素（一直返回同一个随机值） 在并行流下可能返回不同值
        Optional<Integer> q = Stream.of(100,23,43,65).parallel().findAny();
        q.ifPresent(System.out::println);

//        找最小
        Optional<Integer> min = Stream.of(1,2,4,5).min((e,r) -> e.compareTo(r));
        min.ifPresent(System.out::println);

        System.out.println(Stream.of(12, 65, 24, 75).anyMatch(k -> k.equals(24)));

        System.out.println(Stream.of(1, 2, 4, 5).allMatch(f -> f.equals(new ArrayList<Integer>(Arrays.asList(1, 2, 4, 5)))));

        System.out.println(Stream.of(1).allMatch(f -> f.equals(Stream.of(1).collect(Collectors.toList()))));

        System.out.println(Stream.of(1).allMatch(f -> f.equals(Stream.of(1))));

        int[] arr = new int[]{1,3,4,5,6,7,8};
        Stream.of(arr).forEach(System.out::println);

        Arrays.stream(arr).forEach(System.out::print);
    }

    public static void inForEach() {
        List<Integer> ints = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        ints.stream().forEach(i -> {
            if (i.intValue() % 2 == 0) {
                System.out.println("i is even");
            } else {
                System.out.println("i is old");
            }
        });
    }

    public static void inForeach2() {
        List<Integer> ints = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Stream<Integer> evenIntegers = ints.stream().filter(i -> i.intValue() % 2 == 0);
        Stream<Integer> oddIntegers = ints.stream().filter(i -> i.intValue() % 2 != 0);

        evenIntegers.forEach(i -> System.out.println("i is even"));
        oddIntegers.forEach(i -> System.out.println("i is old"));
    }

}
