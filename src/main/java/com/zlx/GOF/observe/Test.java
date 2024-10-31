package com.zlx.GOF.observe;

public class Test {
    public static void main(String[] args) {
        Weather weather = new Weather();

        TemperatureBoard temperature = new TemperatureBoard();
        HumidityBoard humidityBoard = new HumidityBoard();
        PressureBoard pressureBoard = new PressureBoard();
        WeatherStation weatherStation = new WeatherStation(weather,temperature, humidityBoard, pressureBoard);

        weatherStation.onCreate();
//        weather.registerObserver(temperature);
        weatherStation.start();
        System.out.println("================================================");
        weather.setWeather(temperature, 23.10f, 34.33f, 1.03f);


    }
}
