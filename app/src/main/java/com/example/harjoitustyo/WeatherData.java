package com.example.harjoitustyo;

public class WeatherData {
    private String name;
    private String main;
    private String description;
    private String temperature;
    private String windSpeed;

    private String icon;

    private String humidity;

    public WeatherData(String n, String m, String d, String t, String w, String c, String h) {
        name = n;
        main = m;
        description = d;
        temperature = t;
        windSpeed = w;
        icon = c;
        humidity = h;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTemperature() {
        double kelvin = Double.parseDouble(temperature);
        // + 1 is for the round-up.
        Double celsius = new Double(kelvin - 273.15 + 1);
        int intValue = celsius.intValue();
        temperature = String.valueOf(intValue);
        return temperature;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }
}

