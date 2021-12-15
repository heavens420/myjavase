package com.zlx.GOF.builder.builders;

import com.zlx.GOF.builder.product.AirShip;

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
