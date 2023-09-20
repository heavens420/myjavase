package com.zlx.GOF.proxy.cglibdynamicproxy;

/**
 * cglib 动态代理 被代理对象无需实现接口
 */
public class WangWuLawSuit {

    public void submit(String object){
        System.out.println("王五提交证据："+object);
    }

    public void define(){
        System.out.println("王五辩护：铁证如山");
    }
}
