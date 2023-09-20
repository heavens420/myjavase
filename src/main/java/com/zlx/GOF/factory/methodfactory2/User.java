package com.zlx.GOF.factory.methodfactory2;


/**
 *  这里产品product(Operation)用了实体类对象而不是接口 定义了参数 供后续使用
 */
public class User {
    public static void main(String[] args) {
        Operation operation = new OperationFactoryAdd().createOperation();
        operation.setNumberA(100);
        operation.setNumberB(20);
        int result = operation.getResult();

        Operation sqrt = new OperationFactorySqrt().createOperation();
        sqrt.setNumberB(4);
        sqrt.setNumberA(3);
        int result2 = sqrt.getResult();
        System.out.println(result2);
        System.out.println(result);
    }
}
