package com.zlx.GOF.adapter.adapter2.interfaceWay;

/**
 * 这是普通接口 需要转换为通用接口 即需要在通用接口里面调用该接口方法
 */
public class Adaptee {
    public void method(){
        System.out.println("需要被转换的接口 接口方式实现 通过一个中间类去避免目标接口中包含多个方法都必须实现的问题");
    }
}
