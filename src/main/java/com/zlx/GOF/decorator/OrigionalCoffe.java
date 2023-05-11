package com.zlx.GOF.decorator;

public class OrigionalCoffe implements Coffee {
    @Override
    public void makeCoffee() {
        System.out.println("原味coffee");
    }
}
