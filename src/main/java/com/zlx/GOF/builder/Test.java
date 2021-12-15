package com.zlx.GOF.builder;

import com.zlx.GOF.builder.builders.AirShipBuilder;
import com.zlx.GOF.builder.builders.ConcreteAirShipBuilder;
import com.zlx.GOF.builder.director.ConstructorAirShip;
import com.zlx.GOF.builder.product.AirShip;

import java.util.concurrent.Callable;

public class Test {
    public static void main(String[] args) {
        AirShipBuilder shipBuilder = new ConcreteAirShipBuilder();
        ConstructorAirShip constructorAirShip = new ConstructorAirShip(shipBuilder);
        AirShip airShip= constructorAirShip.constructAirShip();
        System.out.println(airShip);

    }
}
