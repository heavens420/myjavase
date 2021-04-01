package com.zlx.javase.mutiextendsinterface;

public interface InterfaceB {
    void say();
    void sayB();

    default void sayKK(){
        System.out.println("BB");
    }
}
