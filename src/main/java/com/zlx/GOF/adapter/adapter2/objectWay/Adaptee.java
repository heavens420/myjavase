package com.zlx.GOF.adapter.adapter2.objectWay;

/**
 * 普通接口 即被适配的接口
 */
public class Adaptee {
    public void method(){
        System.out.println("这是要被适配的普通接口  object模式 通过在具体转换类里面创建被适配对象的方式实现");
    }
}
