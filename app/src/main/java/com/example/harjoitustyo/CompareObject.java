package com.example.harjoitustyo;

public class CompareObject {
    private String weather;
    private String pop;

    private String popChange;


    // I think I don't have any use with this class anymore

    public CompareObject(String weather, String pop, String popChange) {
        this.weather = weather;
        this.pop = pop;
        this.popChange = popChange;
    }

    public String getWeather() {
        return weather;
    }

    public String getPop() {
        return pop;
    }

    public String getPopChange() {
        return popChange;
    }
}
