package com.zlx.GOF.factory.simplefactory;

public class OperationMuti extends Operation {
    @Override
    public int getResult() {
        return this.getNumberA()*this.getNumberB();
    }
}
