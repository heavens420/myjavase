package lambdaTest;

import java.sql.SQLOutput;

import static java.lang.System.*;

public class lambdaTest {
    public static void main(String[] args) {
        //无参 无返回
        interface2 s2 = ()-> System.out.println("这是无参无构造interface");
        s2.a();

        //一参 无返回值
        interface3 s3 = (c)-> out.println(c);
        s3.a(555);

        //有返回值 无参数
        interface4 s4 = ()->{
            out.println("一参无返回执行了");
            return 10;
        };
        int a4 = s4.a();
        out.println(a4);

        //一参有返回
        interface5 s5 = (a)->{
            out.println("一参有返回");
            return a*a;
        };
        int a5 = s5.a(1000);
        out.println(a5);

        //两参（int)有返回
        interface1 s1 = (a,b)-> a+b*b ;
        int a1 = s1.a(1, 3);
        out.println(a1);

        out.println("jjjjjjjjj");
    }


}
