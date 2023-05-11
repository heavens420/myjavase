package com.zlx.GOF.proxy.cglibdynamicproxy;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * cglib动态代理 实现MethodInterceptor接口
 */
public class ProxyLawSuit implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        // 对指定方法执行前 增加其它操作
        if (method.getName().equals("submit")) {
            System.out.println("提交的证据如下：");
        }
        return methodProxy.invokeSuper(o, objects);
    }
}
