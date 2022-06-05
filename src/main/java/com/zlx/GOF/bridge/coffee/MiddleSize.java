package com.zlx.GOF.bridge.coffee;

import com.zlx.GOF.bridge.additives.ICoffeAdditives;

public class MiddleSize extends ExtendCupSize{
    public MiddleSize(ICoffeAdditives coffeAdditives) {
        super(coffeAdditives);
    }

    @Override
    public void orderCoffee(int n) {
        System.out.println("点了"+n+"杯中杯咖啡");
        // 加东西
        coffeAdditives.addSomething();
    }
}
