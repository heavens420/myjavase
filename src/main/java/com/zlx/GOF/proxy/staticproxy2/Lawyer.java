package com.zlx.GOF.proxy.staticproxy2;


/**
 * 静态工厂核心 持有诉讼类接口引用 接收发起诉讼的具体人 并调用具体诉讼人的相关方法
 */
public class Lawyer implements LawSuit{

    private final LawSuit lawSuit;

    public Lawyer(LawSuit lawSuit) {
        this.lawSuit = lawSuit;
    }

    @Override
    public void submit(String object) {
        lawSuit.submit(object);
    }

    @Override
    public void define() {
        lawSuit.define();
    }
}
