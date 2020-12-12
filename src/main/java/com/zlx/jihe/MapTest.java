package com.zlx.jihe;


import java.util.*;

public class MapTest {


    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>(8);
        map.put("aa", "3");
        map.put("cc", "1");
        map.put("dd", "9");

        //只遍历 key 或 value
        for (String s : map.keySet()) {
            System.out.println(s);
        }
        for (String s : map.values()) {
            System.out.println(s);
        }

        //通过entry遍历
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.println(key + ":" + value);
        }

        //通过迭代器 Iterator
        Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> entry = it.next();
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.println(key + ":" + value);
        }

        //曾经最简单的方式 效率较低
        for (String key : map.keySet()) {
            String value = map.get(key);
            System.out.println(key + ":" + value);
        }


        //通过 foreach和lambda表达式遍历
        map.forEach((key, value) -> System.out.println(key + " " + value));

        //通过遍历map.entryset 遍历map
        map.entrySet().forEach(entry -> System.out.println(entry.getKey() + "--" + entry.getValue()));

        List<String> list = new ArrayList<String>(Arrays.asList("ff", "tt"));
        Collections.addAll(list, "1", "2", "3", "dfsfsf");
        Arrays.asList(list, "ll", "qq");
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            String s = (String) iterator.next();
            if (s.equals("3")) {
                iterator.remove();
            }
            System.out.println(s);
        }
        System.out.println(list);


        /*8888888888******************************************?*/
        Map<String,Integer> map1 = new HashMap(8);
        map1.put("q",12);
        map1.put("w",34);
        map1.put("i",9);
        map1.put("r",2);
        //必须用LikedHashMap才能对Integer类型排序，String类型皆可
        Map sortedMap = new LinkedHashMap(8);
        map1.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue().reversed()).forEachOrdered(x->sortedMap.put(x.getKey(),x.getValue()));
        System.out.println(sortedMap);
    }

}
