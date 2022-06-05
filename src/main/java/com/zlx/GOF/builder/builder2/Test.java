package com.zlx.GOF.builder.builder2;

public class Test {
    public static void main(String[] args) {
        // 先创建个指挥者
        Director director = new Director();
        // 再创建具体产品的构建者
        Builder builderProdectA = new ConcreteBuildProductA();
        Builder builderProdectB = new ConcreteBuildProductB();

        // 然后指挥者根据接收到的具体构建者 去创建对应的具体产品
        director.buildParts(builderProdectA);
        // 创建完 返回创建的产品
        final Product productA = builderProdectA.getProduct();
        // 打印看看是不是个人
        System.out.println(productA);

        director.buildParts(builderProdectB);
        final Product productB = builderProdectB.getProduct();
        System.out.println(productB);

    }
}
