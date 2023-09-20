package com.zlx.GOF.decorator;

/**
 * 牛奶咖啡装饰类 扩展原味coffee
 */
public class MilkCoffeeDecorator extends CoffeeDecorator{
    public MilkCoffeeDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public void makeCoffee() {
        super.makeCoffee();
        addMilk();
    }

    public void addMilk(){
        System.out.println("coffee add milk");
    }
}
