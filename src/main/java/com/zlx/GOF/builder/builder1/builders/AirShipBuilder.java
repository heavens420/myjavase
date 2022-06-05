package com.zlx.GOF.builder.builder1.builders;

import com.zlx.GOF.builder.builder1.product.AirShip;

/**
 * 构造器接口
 */
public interface AirShipBuilder {
    // 要构造的组件
    void builderEngine();
    void builderRoom();
    void builderEscapTower();
    AirShip getAirShip();
}
