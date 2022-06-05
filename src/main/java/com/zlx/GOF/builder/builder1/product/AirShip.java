package com.zlx.GOF.builder.builder1.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AirShip {
    // 飞船的组件 引擎 逃逸塔 驾驶舱 可以是一个属性 也可以是一个类
    private String engine;
    private String escapeTower;
    private String room;
}
