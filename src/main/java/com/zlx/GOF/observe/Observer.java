package com.zlx.GOF.observe;

/**
 * 观察者接口
 */
public interface Observer {
    void onNotification(float temperature, float humidity, float pressure);
}
