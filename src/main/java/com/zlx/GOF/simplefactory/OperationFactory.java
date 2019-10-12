package com.zlx.GOF.simplefactory;

public class  OperationFactory {
    public static Operation createOperation(String op){
        Operation operation = null;
        switch (op){
            case "+":
                operation = new OperationAdd();
                break;
            case "-":
                operation = new OperationSub();
                break;
            case "*":
                operation = new OperationMuti();
                break;
            case "/":
                operation = new OperationDiv();
                break;
            default:
                System.out.println("输入有误");
                break;
        }
        return operation;
    }
}
