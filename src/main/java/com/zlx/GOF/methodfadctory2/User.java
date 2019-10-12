package com.zlx.GOF.methodfadctory2;

import java.util.Optional;

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
