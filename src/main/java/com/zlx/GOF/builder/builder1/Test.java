package com.zlx.GOF.builder.builder1;

import com.zlx.GOF.builder.builder1.builders.AirShipBuilder;
import com.zlx.GOF.builder.builder1.builders.ConcreteAirShipBuilder;
import com.zlx.GOF.builder.builder1.director.ConstructorAirShip;
import com.zlx.GOF.builder.builder1.product.AirShip;

public class Test {
    public static void main(String[] args) {
        AirShipBuilder shipBuilder = new ConcreteAirShipBuilder();
        ConstructorAirShip constructorAirShip = new ConstructorAirShip(shipBuilder);
        AirShip airShip= constructorAirShip.constructAirShip();
        System.out.println(airShip);

    }
}
