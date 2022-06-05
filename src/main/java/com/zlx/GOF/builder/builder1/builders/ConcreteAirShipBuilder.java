package com.zlx.GOF.builder.builder1.builders;

import com.zlx.GOF.builder.builder1.product.AirShip;

/**
 * 具体的构造器
 */
public class ConcreteAirShipBuilder implements AirShipBuilder {

    //    private AirShip airShip;
    AirShip airShip = new AirShip();

    @Override
    public void builderEngine() {
        airShip.setEngine("构造了引擎");
        System.out.println("构造了引擎");
    }

    @Override
    public void builderRoom() {
        airShip.setRoom("构造了驾驶舱");
        System.out.println("构造了驾驶舱");
    }

    @Override
    public void builderEscapTower() {
        airShip.setEscapeTower("构造了逃逸塔");
        System.out.println("构造了逃逸塔");
    }

    @Override
    public AirShip getAirShip() {
        return airShip;
    }
}
