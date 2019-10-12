package lambdaTest;

/**
 * 测试JDK1.8 lambda表达式的一些用法 以及接口的新功能
 *
 * 1 接口中的方法不一定都是抽象方法
 * 2 接口中的非抽象方法可以有一些简单的逻辑实现 即 用default或static修饰的方法 可以有多个
 *
 */
public class LambdaExpression {
    public static void main(String[] args) {
        Test test = (a,b)->{
            System.out.println("乘法方法");
            return a*b;
        };
        System.out.println(test.muti(3, 5));

        System.out.println(test.sum(10,20));

        test.sub();
        Test.say();

    }
}

@FunctionalInterface
interface Test{
    public int muti(int x,int y);

    public  default void sub(){
        System.out.println("interface 的简单实现");
    }

    public default int sum(int a,int b){
        return a+b;
    }

    public static void say(){
        System.out.println("hello");
    }
}