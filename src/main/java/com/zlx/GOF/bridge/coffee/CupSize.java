package com.zlx.GOF.bridge.coffee;

import com.zlx.GOF.bridge.additives.ICoffeAdditives;

/**
 * 咖啡杯的类型 大中小等
 */
public abstract class CupSize {
    protected ICoffeAdditives coffeAdditives;

    public CupSize(ICoffeAdditives coffeAdditives) {
        this.coffeAdditives = coffeAdditives;
    }

    public abstract void orderCoffee(int n);
}
