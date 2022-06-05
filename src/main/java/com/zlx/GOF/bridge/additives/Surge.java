package com.zlx.GOF.bridge.additives;

public class Surge implements ICoffeAdditives{
    @Override
    public void addSomething() {
        System.out.println("add surge in coffee");
    }
}
