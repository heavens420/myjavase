package com.zlx.GOF.factory.methodfactory;


public class User {
    public static void main(String[] args) {
        Car audi = new AudiFactory().createCar();
        Car benzi = new BenziFactory().createCar();
        audi.run();
        benzi.run();
    }
}
