package com.zlx.GOF.adapter.adapter2.classWay;

/**
 * 测试类
 * 通过通用接口target 去调用普通接口的方法
 *
 * 与adapter1相比 这里通过转换器(ConcreteAdapterImpl)继承待转换接口(Adaptee)从而直接获得待转换接口 不用显示注入 本质都是获取待转换接口，并无区别
 */
public class Test {
    public static void main(String[] args) {
        Target target = new ConcreteAdapterImpl();
        target.request();
    }
}
