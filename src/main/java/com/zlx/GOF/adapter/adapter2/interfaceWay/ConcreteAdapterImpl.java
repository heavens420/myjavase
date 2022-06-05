package com.zlx.GOF.adapter.adapter2.interfaceWay;


/**
 * 根据需要将普通接口的方法 转换为具体想要的某个接口方法 有选择的转换
 */
public class ConcreteAdapterImpl extends AbstractAdapter {
    // 比如说 这里只需要将普通接口转换为 其中某一个方法 其它的都不需要 那就只具体实现某一个

    private Adaptee adaptee = new Adaptee();

    // 也可以在调用的时候传对应的普通接口对象 进行调用 如下:
//    private Adaptee adaptee;
//
//    public ConcreteAdapterImpl(Adaptee adaptee) {
//        this.adaptee = adaptee;
//    }

    @Override
    public void request1() {
//        super.request1();
        // 具体实现转换方法
        System.out.println("将普通接口的方法转化为 某一种方法 还可以转换为其它很多种方法 但是这里不需要");
        adaptee.method();
    }
}
