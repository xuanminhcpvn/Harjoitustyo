package com.example.harjoitustyo;

import java.util.ArrayList;

public class WeatherDataList {

    private ArrayList<WeatherData> weathers = new ArrayList<>();

    private static WeatherDataList weatherDataStorage = null;

    public static WeatherDataList getInstance() {
        if (weatherDataStorage == null) {
            weatherDataStorage = new WeatherDataList();
        }
            return weatherDataStorage;
        }
    public void addWeatherData(WeatherData weatherData) {
        weathers.add(weatherData);
    }

    public ArrayList<WeatherData> getWeathers() {
        return weathers;
    }
}


