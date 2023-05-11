package com.zlx.GOF.decorator;

public class Client {
    public static void main(String[] args) {
//        originalCoffee();
//        milkCoffee();
        milkAndSurgeCoffee();
    }

    public static void originalCoffee() {
        Coffee coffee = new OrigionalCoffe();
        coffee.makeCoffee();
    }

    public static void milkCoffee() {
        Coffee origionalCoffe = new OrigionalCoffe();
        Coffee coffee = new MilkCoffeeDecorator(origionalCoffe);
        coffee.makeCoffee();
    }

    /**
     * 先加牛奶后加糖
     */
    public static void milkAndSurgeCoffee() {
        Coffee origionalCoffe = new OrigionalCoffe();
        Coffee coffee = new SurgeCoffeeDecorator(new MilkCoffeeDecorator(origionalCoffe));
        coffee.makeCoffee();
    }

}
