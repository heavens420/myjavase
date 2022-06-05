package com.zlx.GOF.adapter.adapter2.classWay;

/**
 * 具体的实现 其它接口到通用接口的转换实现
 * 每一个其它接口与公共接口的转换 都需要一个单独的具体转换实现类
 */
public class ConcreteAdapterImpl extends Adaptee implements Target{
    @Override
    public void request() {
        super.method();
    }
}
