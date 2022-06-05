package com.zlx.GOF.adapter.adapter2.objectWay;

/**
 * 测试方法
 * 通过通用接口target 去调用普通接口中的方法
 */
public class Test {
    public static void main(String[] args) {
        Target target = new ConcreteAdapterImpl();
        target.request();
    }
}
