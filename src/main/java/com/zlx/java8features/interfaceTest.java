package com.zlx.java8features;

/**
 * 接口中通过 default 关键字修饰的方法（非抽象方法）可以有自己的简单实现（虚拟拓展方法）
 */
public interface interfaceTest {

    //通过default 关键字修饰的 有实现的普通方法
    default double calulateSqrt(double a){
        return Math.sqrt(a);
    }

    //正常抽象方法
    double calulate(double d);
}

class AAA{
    public static void main(String[] args) {
        interfaceTest test = new interfaceTest() {
            @Override
            public double calulate(double d) {
                return d + d;
            }
        };

        System.out.println(test.calulate(8));
        System.out.println(test.calulateSqrt(8));
    }
}