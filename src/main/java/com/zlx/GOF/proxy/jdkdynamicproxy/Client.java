package com.zlx.GOF.proxy.jdkdynamicproxy;

import java.lang.reflect.Proxy;

public class Client {
    public static void main(String[] args) {
        Star star = new RealStar();
        StarHandler handler = new StarHandler(star);

//        final Star proxy = (Star) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{Star.class}, handler);
        final Star proxy = (Star) Proxy.newProxyInstance(star.getClass().getClassLoader(), star.getClass().getInterfaces(), handler);
//        proxy.confer();
//        proxy.signConcat();
//        proxy.bookTicket();
        proxy.sing();
//        proxy.collectMoney();
    }
}
