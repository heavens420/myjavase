package com.zlx.GOF.builder.builder2;

/**
 * 具体构建者 具体产品的构建者 想要怎么组合零件 在这里定义
 */
public class ConcreteBuildProductA implements Builder{
    // 先创建一个基础产品对象
    Product product = new Product();

    // 开始构建产品的各个零件的具体细节
    @Override
    public void buildPart1() {
        product.setArm("这是人胳膊");
    }

    @Override
    public void buildPart2() {
        product.setLeg("这是人腿");
    }

    @Override
    public Product getProduct() {
        return product;
    }
}
