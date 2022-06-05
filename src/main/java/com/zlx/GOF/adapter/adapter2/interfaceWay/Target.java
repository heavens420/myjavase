package com.zlx.GOF.adapter.adapter2.interfaceWay;

/**
 * 当通用接口包含很多方法时 并不一定需要去适配所有的普通接口方法 故可以通过一个中间类解决这个问题
 */
public interface Target {
    void request1();
    void request2();
    void request3();
}
