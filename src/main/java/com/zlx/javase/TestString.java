package com.zlx.javase;

/**
 * @Author Zhao LongLong
 * @Date 2020/9/6
 * @Version 1.0
 * @Desc
 */
public class TestString {
    public static void main(String[] args) {
        test1();
    }

    /**
     * 只有直接声明的字符串 可以进入字符串池(位于方法区)，new 出来的对象 放在堆中，
     * 通过intern()方法 会检查字符串池中是否有相同内容的字符串，没有就将该字符串加入字符串池
     *
     */
    public static void test1(){
        String s = "11";
        String a = new String("11");
        String b = new String("11");
        String c = new String("1");
        String d = "1";
        String e = c + d;
        String f = d + d;
        String g = "1" + "1";
        System.out.println(s == b.intern());
        System.out.println(e == a.intern());
        System.out.println(f == a.intern());
        System.out.println(g == a.intern());
        System.out.println(s == f);
    }
}
