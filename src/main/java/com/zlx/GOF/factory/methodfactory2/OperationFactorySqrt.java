package com.zlx.GOF.factory.methodfactory2;

public class OperationFactorySqrt implements OperationFactory {
    @Override
    public Operation createOperation() {
        return new OperationSqrt();
    }
}
