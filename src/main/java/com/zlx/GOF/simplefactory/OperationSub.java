package com.zlx.GOF.simplefactory;

public class OperationSub extends Operation {
    @Override
    public int getResult() {
        return this.getNumberA()-this.getNumberB();
    }
}
