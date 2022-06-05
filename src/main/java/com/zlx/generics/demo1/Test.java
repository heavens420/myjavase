package com.zlx.generics.demo1;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<Dog> dogs = new ArrayList<>();
        int dog = Common.conunt(dogs);
        System.out.println(dog);


        List<Animal> animals = new ArrayList<>();
        int an = Common.conunt(animals);
        System.out.println(an);
    }
}
