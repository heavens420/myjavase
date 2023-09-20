package com.zlx.GOF.factory.methodfactory;

public class AudiFactory implements CarFactory {
    @Override
    public Car createCar() {
        return new Audi();
    }
}
