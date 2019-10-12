package com.zlx.GOF.methodfactory;

public class BenziFactory implements CarFactory {
    @Override
    public Car createCar() {
        return new Benzi();
    }
}
