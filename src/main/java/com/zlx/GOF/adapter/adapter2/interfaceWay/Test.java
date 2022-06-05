package com.zlx.GOF.adapter.adapter2.interfaceWay;

/**
 * 测试类
 * 比如将输入电源电压进行转换
 * 设想，我现在的目标接口有多个方法，可以输出5V，12V，20V的电压。按照正常逻辑，设计一个适配器去实现这个接口，很显然需要实现所有的方法。但是，
 * 实际使用中，其实只需要使用其中一个方法就可以了，比如我mac电脑直流电压20V，只需要实现20V的方法就可以了。
 *
 * 因此，设计一个中间类去把目标接口的所有方法空实现，然后适配器类再去继承这个中间类，选择性重写我所需要的方法，岂不是更好。
 */
public class Test {
    public static void main(String[] args) {
        Target target = new ConcreteAdapterImpl();
        target.request1();
    }

}
