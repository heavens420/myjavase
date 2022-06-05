package com.zlx.GOF.bridge;

import com.zlx.GOF.bridge.additives.Milk;
import com.zlx.GOF.bridge.additives.Surge;
import com.zlx.GOF.bridge.coffee.ExtendCupSize;
import com.zlx.GOF.bridge.coffee.MiddleSize;
import com.zlx.GOF.bridge.coffee.SmallSize;

public class Test {
    public static void main(String[] args) {
        final Milk milk = new Milk();
        ExtendCupSize middle = new MiddleSize(milk);
        middle.orderCoffee(5);
        middle.checkQuality();

        System.out.println("-------------------------");
        ExtendCupSize small = new SmallSize(new Surge());
        small.orderCoffee(10);
        small.checkQuality();
    }
}
