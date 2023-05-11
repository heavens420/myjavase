package com.zlx.GOF.proxy.staticproxy2;

public class ZhangSanLawSuit implements LawSuit{
    @Override
    public void submit(String object) {
        System.out.println("张三提交证据："+ object);
    }

    @Override
    public void define() {
        System.out.println("张三辩护：铁证如山");
    }
}
