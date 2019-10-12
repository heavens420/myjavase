package com.zlx.GOF.methodfactory;

public class User {
    public static void main(String[] args) {
        Car audi = new AudiFactory().createCar();
        Car benzi = new BenziFactory().createCar();
        audi.run();
        benzi.run();
    }
}
