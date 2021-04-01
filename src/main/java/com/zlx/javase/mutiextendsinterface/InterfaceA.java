package com.zlx.javase.mutiextendsinterface;

public interface InterfaceA extends InterfaceB,InterfaceC{

    void sayA();


    //  B  C 接口都定义了 say()方法  重写不区分是哪个接口的say()方法
    @Override
    void say();

    @Override
    void sayB();

    // 对于两个接口都有default修饰的sayKK()方法,需要重写该方法 且同样不区分是哪个接口的
    @Override
    default void sayKK() {

    }

    @Override
    void sayC();
}
