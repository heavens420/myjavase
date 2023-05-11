package com.zlx;

import cn.hutool.core.map.MapUtil;
import com.alibaba.fastjson.JSONObject;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @Author Zhao LongLong
 * @Date 2020-11-3
 * @Version 1.0
 * @Desc 当double类型的数据太大或者太小的时候，会自动转换为科学计数法，此时将其转化为String类型的时候 会是科学计数法形式，不是想要的形式
 * 可以通过BigDecimal类将科学计数法转化为正常形式，但是，double在自动将一个很大或很小的数转化为科学计数法的时候可能会丢失精度，
 * 这样一来，自动转化后的科学计数法形式的值将不会和转化前相等，所以当将其转化为字符串再转化回去的时候也不会相等。
 */


public class Test {
    public static void main(String[] args) throws Exception {
//        testOptional();
//        testStringJoiner();
//        boolean ss = getBoolean();
//        Boolean ss2 = getBoolean();
//        System.out.println(ss);
//        System.out.println(ss2);

        // 1 = 0000 0001
        // -1 = 1000 0001
        // -2 = 1000 0010
//        System.out.println(1L << -129);
//        System.out.println(1L << -1);
//        testCast();

        Map<String, String> map = new HashMap<>();
        map.put("12", "fw");
        map.put("12a", "ffaw");
        map.put("1223r", "ffafgw");

        String fsafasd = map.get("fsafasd");

        Integer sdsd = MapUtil.getInt(map, "sdsd");
        System.out.println(sdsd);

        if (true || false) {
            System.out.println("w3232");
        }

//        System.out.println(JSONObject.toJSONString(map));

//        int a = 1;
//
//        try {
//            System.out.println(a / 0);
//        } catch (Exception e) {
//            System.out.println("-------------getMessage-------------------");
//            System.out.println(e.getMessage());
//                System.out.println("-------------getStackTrace-------------------");
//            System.out.println(Arrays.toString(e.getStackTrace()));
//            System.out.println("---------getCause-----------------------");
//            e.getCause();
//            System.out.println("----------------printStackTrace----------------");
//            e.printStackTrace();
//        }
    }

    public static void testCast() {
        Object[] array = {Integer.MAX_VALUE * 100, Integer.MAX_VALUE / 200, Integer.MAX_VALUE / 300, new int[]{}, "999"};
        String ss = array[0].toString();
        System.out.println(ss);
        Long lon = Long.parseLong(ss);
        System.out.println(lon);
    }

    public static void testDuble() {
        Double d = 0.00040000000000000001;
        Double ds = 1000000000000000000000001000000.00;

        System.out.println(ds == ds - 1);
        Long i = Long.MAX_VALUE;
        System.out.println(i);
        Double d1 = i + d;
        String d2 = String.valueOf(d1);
//        String d3 = d2 - i;

        String s = String.valueOf(d);
        String sd = String.valueOf(ds);
        System.out.println(s + "fafafa");
        System.out.println(sd);

        System.out.println(Double.valueOf(s));

        if (s.contains("E")) {
            System.out.println("科学计数法");
            BigDecimal decimal = new BigDecimal(s);
            System.out.println(decimal.toPlainString());
        }
    }

    public static void testOptional() {
        String str = "";

        // str 非空 同样会执行 orElse()方法
        Optional.ofNullable(str).orElse(sayHello());
//        Optional.ofNullable(str).orElseThrow(new NullPointerException(""))
    }

    public static String sayHello() {
        System.out.println("hello");
        return "hello";
    }

    public static void testIf() {
//        Dictionary optional = new Hashtable<String,Action>();
//
//        optional["11"] = () ->{
//
//        };
    }

    public static void testStringJoiner() {
        StringJoiner joiner = new StringJoiner(",", "[", "]");
        IntStream.range(1, 10).forEach(i -> joiner.add(i + ""));
        System.out.println(joiner.toString());
    }

    public static Boolean getBoolean() {
        return null;
    }


    public static void calculate() {
        int sum = 6989;
        int z = 0, x = 0, c = 0, v = 0, b = 0;
        while (true) {
            z++;
            if (z >= 574) {

            }
            if (z + x + c + v + b == 574) {
                System.out.println("z:"+z);
                System.out.println("x:"+x);
                System.out.println("c:"+c);
                System.out.println("v:"+v);
                System.out.println("b:"+b);
                break;
            }

        }
    }

    public static void calculating(int sum,int n){
        int mid = sum/n;

        for (int i = 0; i < n; i++) {

        }
    }

    public static void forMap(){
        List<Map<String,Object>> list = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        map.put("dicId","aaa");
        map.put("dicName","钢材");
        map.put("dicDes","钢材1号");
        list.add(map);

        map = new HashMap<>();
        map.put("dicId","bbb");
        map.put("dicName","钢筋");
        map.put("dicDes","钢筋1号");
        list.add(map);
        map = new HashMap<>();
        map.put("dicId","aaa");
        map.put("dicName","钢筋");
        map.put("dicDes","钢筋2号");
        list.add(map);

        map = new HashMap<>();
        map.put("dicId","bbb");
        map.put("dicName","河沙");
        map.put("dicDes","河沙1号");
        list.add(map);
//        Map<String, List<Map<String, Object>>> collect = list.stream().collect(Collectors.groupingBy(this::customKey));
//        Map<String, List<Map.Entry<String, Object>>> collect = list.stream().flatMap(m -> m.entrySet().stream()).collect(Collectors.groupingBy(Map.Entry::getKey));
        Map<Object, List<Map<String, Object>>> collect = list.stream().collect(Collectors.groupingBy(x -> x.get("dicId")));
        System.out.println(collect);
    }
}
