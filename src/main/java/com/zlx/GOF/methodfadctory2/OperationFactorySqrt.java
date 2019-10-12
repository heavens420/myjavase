package com.zlx.GOF.methodfadctory2;

public class OperationFactorySqrt implements OperationFactory {
    @Override
    public Operation createOperation() {
        return new OperationSqrt();
    }
}
