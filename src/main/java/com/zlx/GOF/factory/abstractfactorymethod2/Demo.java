package com.zlx.GOF.factory.abstractfactorymethod2;


import com.zlx.GOF.factory.abstractfactorymethod.Chair;

/**
 * 根据不同的系统 创建不同的button和checkbox
 * 可以新增linux系统并创建linux创建button和checkbox的工厂
 * 与前两个demo相比 这里创建了两个product(Button和CheckBox，在factory中能够体现)并且这里构造了application客户端类 使用时需要传接口类型
 * 即：抽象工厂创建的不是一个单一产品(比如只创建car)，而是创建一个一系列产品(一个产品族)。本质来说二者的区别就是创建一种单一产品还是多种不同产品。
 */
public class Demo {

    private static Application configuration(){
        Factory factory = null;
        Application application;
        
        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.contains("windows")) {
            factory = new WindowsFactory();
        } else if (osName.contains("mac")) {
            factory = new MacFactory();
        }
        assert factory != null;
        application = new Application(factory);
        return application;
    }

    public static void main(String[] args) {
        new Chair("3");
        new Chair("e", "fd");
        Application application = configuration();
        application.paint();
    }
}

