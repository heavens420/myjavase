package com.zlx.GOF.builder.builder2;

/**
 * 构建器　定义构建方法
 */
public interface Builder {
    void buildPart1();
    void buildPart2();
    Product getProduct();
}
