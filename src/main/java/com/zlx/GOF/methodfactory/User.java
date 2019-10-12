package com.zlx.GOF.methodfactory;


import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class User {
    public static void main(String[] args) {
        Car audi = new AudiFactory().createCar();
        Car benzi = new BenziFactory().createCar();
        audi.run();
        benzi.run();
    }
}
