package com.zlx.GOF.builder.builder2;

/**
 * 指导者 调用构建产品的方法
 */
public class Director {
    public void buildParts(Builder builder){
        builder.buildPart1();
        builder.buildPart2();
    }
}
