package com.zlx.GOF.builder.director;

import com.zlx.GOF.builder.builders.AirShipBuilder;
import com.zlx.GOF.builder.product.AirShip;
import org.springframework.beans.factory.annotation.Autowired;

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
