package com.zlx.GOF.proxy.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class StarHandler implements InvocationHandler {

    private Star realStar;

    public StarHandler(Star realStar) {
        this.realStar = realStar;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object object = null;
        realStar.confer();
        realStar.signConcat();
        realStar.bookTicket();
        if ("sing".equals(method.getName())) {
            object = method.invoke(realStar, args);
        }
        realStar.collectMoney();
        return object;
    }
}
