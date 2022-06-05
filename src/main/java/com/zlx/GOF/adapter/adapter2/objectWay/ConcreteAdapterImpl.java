package com.zlx.GOF.adapter.adapter2.objectWay;

/**
 * 普通接口要转换为通用接口的具体实现类
 * 每一个普通接口到通用接口的转换都对应着一个具体的转换实现类
 */
public class ConcreteAdapterImpl implements Target{
    private Adaptee adaptee = new Adaptee();

    // 也可以在调用的时候传对应的普通接口对象 进行调用 如下:
//    private Adaptee adaptee;
//
//    public ConcreteAdapterImpl(Adaptee adaptee) {
//        this.adaptee = adaptee;
//    }

    @Override
    public void request() {
        adaptee.method();
    }
}
