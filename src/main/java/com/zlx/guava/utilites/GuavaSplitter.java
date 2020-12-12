package com.zlx.guava.utilites;

import com.google.common.base.Splitter;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @Author Zhao LongLong
 * @Date 2020/9/3
 * @Version 1.0
 * @Desc Splitter执行与Joiner相反的功能，它可以去除字符串间的分隔符
 */
public class GuavaSplitter {
    public static void main(String[] args) {

        JavaSplit();
        guavaSplit();

    }

    /**
     * 传统Java spilt方法分割 无法自定义分割
     */
    public static void JavaSplit(){
        String str = "sun,mon,,tue,wend,,";
        String[] split = str.split(",");
//        Arrays.stream(split).forEach(System.out::println);
        System.out.println(Arrays.toString(split));
    }
    
    public static void guavaSplit(){
        String str = "sun,mon,,tue,wend,,";
        Iterable<String> split = Splitter.on(",").split(str);
        split.forEach(s -> System.out.println(s));
//        System.out.println(Arrays.toString(split));
    }
}
