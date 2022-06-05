package com.zlx.GOF.builder.builder1.director;

import com.zlx.GOF.builder.builder1.builders.AirShipBuilder;
import com.zlx.GOF.builder.builder1.product.AirShip;

/**
 * 飞船组装
 */
public class ConstructorAirShip {
    private AirShipBuilder airShipBuilder;

    public ConstructorAirShip(AirShipBuilder airShipBuilder) {
        this.airShipBuilder = airShipBuilder;
    }

    /**
     * 构建飞船
     * @return
     */
    public AirShip constructAirShip(){
        airShipBuilder.builderEngine();
        airShipBuilder.builderRoom();
        airShipBuilder.builderEscapTower();
        return airShipBuilder.getAirShip();
    }
}
