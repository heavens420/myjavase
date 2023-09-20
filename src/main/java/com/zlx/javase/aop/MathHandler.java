package com.zlx.javase.aop;

public class MathHandler {

    public int add(int x, int y) {
        System.out.println("计算开始--------------------------------> x + y 的值");
        return x + y;
    }
}
