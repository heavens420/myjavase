package com.zlx.GOF.decorator;

/**
 * coffee装饰器 用于增强coffe种类
 */
public abstract class CoffeeDecorator implements Coffee {
    private final Coffee coffee;

    public CoffeeDecorator(Coffee coffee) {
        this.coffee = coffee;
    }

    @Override
    public void makeCoffee() {
        coffee.makeCoffee();
    }
}
