package com.zlx.GOF.factory.methodfactory2;

public class OperationAdd extends Operation {

    //重写父类计算方法
    @Override
    public int getResult() {
        return this.getNumberA()+this.getNumberB();
    }
}
