package lambdaTest;

import java.sql.SQLOutput;

public class lambdaTest2 {
    public static void main(String[] args) {
        //常规方式
        interface1 s1 = (h, b) -> (int) Math.pow(h, b);
        int a1 = s1.a(2, 3);
        System.out.println(a1);

        //引用方式
        interface1 s2 = (c,d)->change(c,d);
        int a2 = s2.a(4,2);
        System.out.println(a2);

        //引用方式
        interface1 s3 = lambdaTest2::change;
        int a3 = s3.a(9,2);
        System.out.println(a3);

        interface3 s4 = s -> change2(s);
        s4.a(7997);
    }

    public static int change(int v,int l){
        System.out.println("这是两参数 有返回测试");
        return (int)Math.pow(v,l);
    }

    private static void change2(int s){
        System.out.println("zhe shi 一个 测试");
        System.out.println("hhhhhhhhh");
    }
}
