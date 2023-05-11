package com.zlx.GOF.proxy.staticproxy2;

public class LisiLawSuit implements LawSuit{
    @Override
    public void submit(String object) {
        System.out.println("李四提交证据："+ object);
    }

    @Override
    public void define() {
        System.out.println("李四辩护：铁证如山");
    }
}
