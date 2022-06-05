package com.zlx.GOF.adapter.adapter2.classWay;

/**
 * 测试类
 * 通过通用接口target 去调用普通接口的方法
 */
public class Test {
    public static void main(String[] args) {
        Target target = new ConcreteAdapterImpl();
        target.request();
    }
}
