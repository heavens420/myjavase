package com.zlx.GOF.adapter.adapter2.interfaceWay;

/**
 * 通过一个中间类去实现目标通用接口的所有方法 但不具体实现 谁用谁实现
 */
public abstract class AbstractAdapter implements Target{
    @Override
    public void request1() {

    }

    @Override
    public void request2() {

    }

    @Override
    public void request3() {

    }
}
