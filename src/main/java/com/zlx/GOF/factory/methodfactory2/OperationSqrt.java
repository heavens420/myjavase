package com.zlx.GOF.factory.methodfactory2;

public class OperationSqrt extends Operation {
    @Override
    public int getResult() {
        return this.getNumberA()*this.getNumberA()+this.getNumberB()*this.getNumberB();
    }
}
