package com.zlx.GOF.factory.methodfactory2;

public class OperationFactoryAdd implements OperationFactory {
    @Override
    public Operation createOperation() {
        return new OperationAdd();
    }
}
