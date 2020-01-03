package com.zlx.java8features;

import java.util.*;

/**
 * java8 中新增foreach 方法 快速遍历
 */
public class forEacch {
    public static void main(String[] args) {

        //foreach遍历
        List list = new ArrayList(Arrays.asList("ffaf","gaghagh","gha","gag"));

        list.forEach(s -> {
            System.out.println(s);
        });

        //通过lambda表达式+foreach 遍历
        list.forEach(System.out::println);

        //foreach 遍历Map
        Map<String,String> map = new HashMap<String, String>();
        map.put("name","zhangsan");
        map.put("age", "800");
        map.put("hobby", "playing");

        map.forEach((key,value) -> System.out.println(key+"  "+value));

        //foreach 遍历map.entyset
        map.entrySet().forEach(entry -> System.out.println(entry.getKey()+" "+entry.getValue()));
    }
}
