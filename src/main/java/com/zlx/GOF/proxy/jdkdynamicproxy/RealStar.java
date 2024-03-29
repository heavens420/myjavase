package com.zlx.GOF.proxy.jdkdynamicproxy;

/**
 * jdk动态代理 被代理的类必须实现接口
 */
public class RealStar implements Star {
    @Override
    public void confer() {
        System.out.println("代理人面谈");
    }

    @Override
    public void signConcat() {
        System.out.println("代理人签合同");
    }

    @Override
    public void bookTicket() {
        System.out.println("代理人订票");
    }

    @Override
    public void sing() {
        System.out.println("明星本人唱歌");
    }

    @Override
    public void collectMoney() {
        System.out.println("代理人收钱");
    }
}
