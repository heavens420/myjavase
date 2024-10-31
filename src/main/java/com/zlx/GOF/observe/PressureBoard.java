package com.zlx.GOF.observe;

import lombok.Data;

/**
 * 压强变化时 展示最新天气情况
 */
@Data
public class PressureBoard implements Board,Observer  {

    // 温度
    private float temperature;
    // 湿度
    private float humidity;
    // 压强
    private float pressure;

    @Override
    public void display() {
//        System.out.println("天气情况如下：");
//        System.out.println("当前温度：" + temperature);
//        System.out.println("当前湿度：" + humidity);
        System.out.println("当前压力：" + pressure);
//        System.out.println("天气预报结束");
    }

    @Override
    public void onNotification(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        this.display();
    }
}
