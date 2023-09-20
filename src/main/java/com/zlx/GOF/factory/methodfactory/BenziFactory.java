package com.zlx.GOF.factory.methodfactory;

public class BenziFactory implements CarFactory {
    @Override
    public Car createCar() {
        return new Benzi();
    }
}
