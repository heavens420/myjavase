package com.zlx.GOF.bridge.additives;

public class Milk implements ICoffeAdditives{
    @Override
    public void addSomething() {
        System.out.println("add milk in coffee");
    }
}
