package com.zlx.GOF.methodfadctory2;

public class OperationFactoryAdd implements OperationFactory {
    @Override
    public Operation createOperation() {
        return new OperationAdd();
    }
}
