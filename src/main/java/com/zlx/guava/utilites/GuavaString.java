package com.zlx.guava.utilites;

import com.google.common.base.Strings;

/**
 * @Author Zhao LongLong
 * @Date 2020/9/8
 * @Version 1.0
 * @Desc
 */
public class GuavaString {
    public static void main(String[] args) {
        Test1();
        Test2();
        Test3();
    }

    public static void Test1(){
        // 在字符串结尾加 字符（char） 参1：被追加的字符串 参2：追加后的字符串最小长度，当该长度超过参数1的长度时，不会追加 参3：要追加的字符
        String f = Strings.padEnd("123", 3, 'f');
        String s = Strings.padStart("789", 6, '0');
        System.out.println(f);
        System.out.println(s);
    }

    public static void Test2(){
        // 空格和tab
        String s = "jfsljf  kflk    ko kl";
        System.out.println(s.replaceAll(" ","").replaceAll(" ",""));
    }

    public static void Test3(){
        String s = "    ";
        // 空格 tab 认为非空 同 isEmpty()方法
        System.out.println(Strings.isNullOrEmpty(s));

        System.out.println(" ".isEmpty());
    }
}
