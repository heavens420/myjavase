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
     * 通过intern()方法 会检查字符串池中是否有相同内容的字符串引用，没有就将该字符串的引用加入字符串池
     *
     * JDK 1.7后，intern方法还是会先去查询常量池中是否有已经存在，如果存在，则返回常量池中的引用，这一点与之前没有区别，
     * 区别在于，如果在常量池找不到对应的字符串，则不会再将字符串拷贝到常量池，而只是在常量池中生成一个对原字符串的引用。
     * 简单的说，就是往常量池放的东西变了：原来在常量池中找不到时，复制一个副本放到常量池，1.7后则是将在堆上的地址引用复制到常量池。
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
        System.out.println(s == b.intern());  // true
        System.out.println(s.intern() == b);  // false  intern() 将堆上的引用加到字符串常量区 注意是堆-->常量区，对已在常量区的字符串intern() 无意义
        System.out.println(e == a.intern());  // false
        System.out.println(e.intern() == a.intern());  // true
        System.out.println(f == a.intern());  // false
        System.out.println(g == a.intern());  // true
        System.out.println(s == f);           // false
    }
}
