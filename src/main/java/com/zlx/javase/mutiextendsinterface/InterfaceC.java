package com.zlx.javase.mutiextendsinterface;

public interface InterfaceC {
    void say();
    void sayC();

    default void sayKK(){
        System.out.println("CC");
    }
}
