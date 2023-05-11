package com.zlx.GOF.factory.simplefactory;

public class OperationDiv extends Operation {
    @Override
    public int getResult() {
        if (this.getNumberB()!=0){
            return this.getNumberA()/getNumberB();
        }else {
            System.out.println("除数不能为0");
            return -1;
        }
    }
}
