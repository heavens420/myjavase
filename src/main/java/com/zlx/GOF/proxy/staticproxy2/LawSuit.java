package com.zlx.GOF.proxy.staticproxy2;

/**
 * 诉讼接口
 * 谁诉讼 谁实现
 */
public interface LawSuit {
    /**
     * 提交证据
     */
    void submit(String object);

    /**
     * 辩护
     */
    void define();
}
