package com.zlx.guava.utilites;

import com.google.common.base.Joiner;

import java.util.*;
import java.util.stream.Stream;

/**
 * Joiner
 */
public class TestJoiner {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>(Arrays.asList("niaho", "henhao", "buhao", "zhenhao"));
        List<String> listWithNull = new ArrayList<>(Arrays.asList("niaho", "henhao", "buhao", "zhenhao",null));

        StringBuilder builder = new StringBuilder();

        String joinList = Joiner.on("--").join(list);

        //通过 skipNulls() 方法去除null值，避免出现NPE
        String joinListWithNull = Joiner.on("++").skipNulls().join(listWithNull);

        //通过 useForNull() 方法 替换null值
        String replaceNull = Joiner.on("#").useForNull("replaceNull").join(listWithNull);

        // 将 listWithNull添加到builder后面
        StringBuilder stringBuilder = Joiner.on("--").useForNull("replaceNull").appendTo(builder, listWithNull);

        System.out.println(joinList);
        System.out.println(joinListWithNull);
        System.out.println(replaceNull);
        System.out.println(stringBuilder);

        // stream 给null 加默认值 但是有缺陷 末尾的 - 没去掉
        listWithNull.stream().flatMap(s -> s == null? Stream.of("kkk") :Stream.of(s)).forEach(s -> System.out.print(s+"-"));

        System.out.println();
        //MapJoiner 将map转换为String，并key value定义分隔符
        mapJoiner();
    }

    public static String mapJoiner(){
        Map map  = new HashMap(8);
        map.put("name","nana");
        map.put("age",18);
        map.put("sex", "girl");
        map.put("phone", "secret");
        map.put("hobby","no comment");
        String joiner = Joiner.on("#").withKeyValueSeparator("::").join(map);
        System.out.println(joiner);
        return joiner;
    }
}
