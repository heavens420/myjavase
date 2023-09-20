package com.zlx.GOF.proxy.cglibdynamicproxy;

import org.springframework.cglib.proxy.Enhancer;

/**
 * 代理工厂 生成代理对象 也可以省略该类
 */
public class ProxyFactory {

    public static Object createProxyBean(Object target){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(new ProxyLawSuit());
        Object proxyObject = enhancer.create();
        return proxyObject;
    }
}
