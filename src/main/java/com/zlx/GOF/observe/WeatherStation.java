package com.zlx.GOF.observe;

import lombok.Data;

/**
 * 气象站
 */
@Data
public class WeatherStation {

    private Weather weather;
    private TemperatureBoard temperatureBoard;
    private HumidityBoard humidityBoard;
    private PressureBoard pressureBoard;

    public WeatherStation(Weather weather,TemperatureBoard temperatureBoard, HumidityBoard humidityBoard, PressureBoard pressureBoard) {
        this.weather = weather;
        this.temperatureBoard = temperatureBoard;
        this.humidityBoard = humidityBoard;
        this.pressureBoard = pressureBoard;
    }

    public void onCreate() {
        weather.registerObserver(temperatureBoard);
        weather.registerObserver(humidityBoard);
        weather.registerObserver(pressureBoard);
    }

    public void register(Observer observer) {
        weather.registerObserver(observer);

    }

    public void unregister(Observer observer) {
        weather.removeObserver(observer);
    }

    public void start() {
        System.out.println("==========No1===========");
        weather.setWeather(23.9f,50.2f,1.02f);
        System.out.println("==========No2===========");
        weather.setWeather(21.9f,52.3f,1.01f);
        System.out.println("==========No3===========");
        weather.setWeather(24.9f,49.9f,1.03f);
    }
}
