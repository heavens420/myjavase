package com.zlx.GOF.bridge.coffee;

import com.zlx.GOF.bridge.additives.ICoffeAdditives;

import java.util.Random;

/**
 * CupSize的修正类
 */
public abstract class ExtendCupSize extends CupSize{
    public ExtendCupSize(ICoffeAdditives coffeAdditives) {
        super(coffeAdditives);
    }

    public void checkQuality(){
        Random ran = new Random();
        System.out.println(String.format("%s 添加%s", coffeAdditives.getClass().getSimpleName(), ran.nextBoolean() ? "太多" : "正常"));
    }
}
