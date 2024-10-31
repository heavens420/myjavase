package com.zlx.GOF.observe;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Weather implements Observable{

    // 温度
    private float temperature;
    // 湿度
    private float humidity;
    // 压强
    private float pressure;

    List<Observer> observers = new ArrayList<Observer>();

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObserver(Observer observer) {
        for (Observer obs : observers) {
            if (obs == observer) {
                obs.onNotification(temperature, humidity, pressure);
            }
        }
    }

    @Override
    public void notifyObservers() {
        for (Observer obs : observers) {
            obs.onNotification(temperature, humidity, pressure);
        }
    }

    public void setWeather(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        this.notifyObservers();
    }

    public void setWeather(Observer observer,float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        this.notifyObserver(observer);
    }
}
