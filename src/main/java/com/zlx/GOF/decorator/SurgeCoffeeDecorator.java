package com.zlx.GOF.decorator;

/**
 * 加糖咖啡装饰类 扩展原味coffee
 */
public class SurgeCoffeeDecorator extends CoffeeDecorator{
    public SurgeCoffeeDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public void makeCoffee() {
        super.makeCoffee();
        addSurge();
    }

    public void addSurge(){
        System.out.println("coffee add surge");
    }
}
