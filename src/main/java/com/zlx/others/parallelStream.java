package com.zlx.others;

import java.lang.invoke.CallSite;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class parallelStream {
    public static void main(String[] args) {
        int max = 1000000;
        List<String> list = new ArrayList<>(max);
        for (int i = 0; i < max; i++) {
            UUID uuid = UUID.randomUUID();
            list.add(uuid.toString());
        }

        //获取当前系统时间
        long currentTime1 = System.nanoTime();
        //串行排序
        long num1 = list.stream().sorted().count();
        //获取当前系统时间
        long currentTime2 = System.nanoTime();

        long currentTime3 = System.nanoTime();
        //并行排序
        //long num2 = list.parallelStream().sorted().count();
//        list.parallelStream().forEach(System.out::println);
        long currentTime4 = System.nanoTime();

        System.out.println("串行排序时间:"+(currentTime2 - currentTime1));
        System.out.println("并行排序时间:"+(currentTime4 - currentTime3));
    }
}
