package com.zlx.GOF.proxy.jdkdynamicproxy;

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
        // 要执行的公告方法
        realStar.confer();
        realStar.signConcat();
        realStar.bookTicket();

        // 调用特定方法(sing)时才执行 否则不执行该方法
        if ("sing".equals(method.getName())) {
            object = method.invoke(realStar, args);
        }
        realStar.collectMoney();
        return object;
    }
}
