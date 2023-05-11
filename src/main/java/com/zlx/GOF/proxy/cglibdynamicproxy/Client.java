package com.zlx.GOF.proxy.cglibdynamicproxy;

public class Client {
    public static void main(String[] args) {
        WangWuLawSuit wangWuLawSuit = (WangWuLawSuit) ProxyFactory.createProxyBean(new WangWuLawSuit());
        wangWuLawSuit.submit("人证物证具在");
        wangWuLawSuit.define();
    }
}
