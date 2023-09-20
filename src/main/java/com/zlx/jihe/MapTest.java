package com.zlx.jihe;

import cn.hutool.core.map.MapUtil;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class MapTest {


    public static void main(String[] args) {
//       foreachList();
//       testStream();
//       treeMap();
//        testCommonOperators();
//        hashTable();
//        mapOperations();
//        orderMap();
//        Integer integer = Integer.valueOf("");
//        System.out.println(integer);

        Map<String, Object> param = new HashMap<>();
        // 客户名称
        param.put("requestType", "2022003");
        param.put("org_customer_manage", "0");
        param.put("userID", "");
        param.put("crm_web_cust_id", ""); //客户ID
        param.put("customer_code", ""); //客户编码
        param.put("isLike", "1"); //是否是模糊查询

        Map<String,Object> params = new HashMap<>();
        param.put("customerName", MapUtil.getStr(params, "customerName"));
        Map map = MapUtil.get(param, "list", Map.class);
        System.out.println(map);

    }

    /**
     * 遍历集合中的map 并将map的值存入新的集合
     */
    public static void foreachList() {
        List<Map<String, String>> list = new ArrayList<>();
        Map mapA = new HashMap<String, String>();
        mapA.put("A", "A1");

        Map mapB = new HashMap<String, String>();
        mapB.put("B", "B1");

        list.add(mapB);
        list.add(mapA);

//        List newList = new ArrayList();
//
//        list.forEach(it ->{
//            it.entrySet().stream().forEach(m -> newList.add(m.getValue()));
//        });

        final List<String> listStream = list.stream().flatMap(m -> m.values().stream()).collect(Collectors.toList());

//        newList.forEach(System.out::println);

        System.out.println(listStream);
    }

    public static void testStream() {
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

        //曾经 最简单的方式 效率较低
        for (String key : map.keySet()) {
            String value = map.get(key);
            System.out.println(key + ":" + value);
        }


        //通过 foreach和lambda表达式遍历
        map.forEach((key, value) -> System.out.println(key + " " + value));

        //通过遍历map.entryset+stream 遍历map
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

    }

    public static void orderMap() {
        /*8888888888******************************************?*/
        Map<String, Integer> map1 = new HashMap(8);
        map1.put("q", 12);
        map1.put("w", 34);
        map1.put("i", 9);
        map1.put("r", 20);
        map1.put("re", 2);
        map1.put("re", 2333);
        map1.put("s", 2333);
        //必须用LikedHashMap才能对Integer类型排序，String类型皆可
        Map<String,Integer> sortedMap = new LinkedHashMap<>(8);
        map1.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue().reversed()).forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));
        LinkedHashMap<String, Integer> collect = map1.entrySet().stream().sorted((v1, v2) -> v1.getValue().compareTo(v2.getValue())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, Integer::sum, LinkedHashMap::new));
        System.out.println("----------------------------------------------------------------");
        System.out.println("排序map1"+sortedMap);
        System.out.println("排序map2"+collect);
    }

    public static void mapOperations() {
        Map<String,String> map = new HashMap<>();
        map.put("foo", "bar");
        map.put("bar", "baz");
        map.get("bar12");
        map.remove("bar");
        map.clear();
    }

    public static void linkedHashMap() {
        Map<String, Integer> map = new LinkedHashMap<>(8);
        map.put("123", 3);
        map.put("name", 456);
        map.put("kkk", 789);
        map.forEach((k, v) -> System.out.println(k + "=" + v));
    }

    public static void hashTable() {
//        Map<String, String> map = new Hashtable<>();
        Map<String, String> map = new HashMap<>();
//        Map<String, String> reduce = Stream.iterate(1L,x-> x < 2000000 ,x -> x + 1).reduce(map,
//                (a, b) -> {
//                    a.put(b+"", b+"");
//                    return a;
//                }, (u, t) -> {
//                    t.forEach(u::put);
////                    u.putAll(t);
//                    return u;
//                });
//        reduce.forEach((k, v) -> System.out.println(k + "=" + v));

        try {
            for (int i = 0; i < 100; i++) {
                int finalI = i;
                new Thread(() -> {
                    for (int j = 0; j < 100; j++) {
                        String name = Thread.currentThread().getName();
                        long id = Thread.currentThread().getId();
                        map.put(id + "-" + finalI + "-" + j, name + finalI);
                    }
                }).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        map.forEach((k, v) -> System.out.println(k + "=" + v));
    }

    public static void treeMap() {
        Map<String, Integer> map = new TreeMap<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                // 默认此方式排序 即根据字符串的ascii码升序
                return o1.compareTo(o2);
                // 自定义根据key的长度排序
//                return o1.length() - o2.length();
                //
//                return map.get(o1) - map.get(o2);
            }
        });
        map.put("id", 123);
        map.put("name", 456);
        map.put("kkk", 789);
        map.put("ddd", 333);
        map.forEach((k, v) -> System.out.println(k + "=" + v));
        System.out.println("================================================================");
        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });
        map.forEach((k, v) -> System.out.println(k + "=" + v));
        System.out.println("----------------------------------------------------------------");
        list.forEach(x -> System.out.println(x.getKey() + "=" + x.getValue()));

        System.out.println("****************************************************************");
        LinkedHashMap<String, Integer> reduce = list.stream().reduce(new LinkedHashMap<>(), (x, y) -> {
            x.put(y.getKey(), y.getValue());
            return x;
        }, (u, t) -> null);
        reduce.forEach((k, v) -> System.out.println(k + "=" + v));
    }

    public static void testCommonOperators() {
        Map<String, Integer> map = new HashMap<>(8);
        map.put("123", 3);
        map.put("name", 456);
        map.put("kkk", 789);
        map.put("123", 444);
        map.forEach((k, v) -> System.out.println(k + "=" + v));
    }
}
