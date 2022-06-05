package com.zlx.GOF.builder.builder2;

public class ConcreteBuildProductB implements Builder{
    // 创建基础对象
    Product product = new Product();

    // 具体对象零件的创建细节
    @Override
    public void buildPart1() {
        product.setLeg("按100个腿 做个千足虫看看");
    }

    @Override
    public void buildPart2() {
        product.setEye("按8个眼睛 我怕你不够用");
    }

    @Override
    public Product getProduct() {
        return product;
    }
}
