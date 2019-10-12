package com.zlx.GOF.simplefactory;

public class Client {
    public static void main(String[] args) {
        String op = "/";
        Operation operation = OperationFactory.createOperation(op);
        operation.setNumberA(100);
        operation.setNumberB(0);
        int result = operation.getResult();
        System.out.println(result);
    }
}
