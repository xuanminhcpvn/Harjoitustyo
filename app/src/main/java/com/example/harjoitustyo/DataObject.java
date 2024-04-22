package com.example.harjoitustyo;

public class DataObject {

    private String popData;
    private String weatherData;
    private String EmploymentData;
    private String PoliticalData;

    public DataObject(String popData, String weatherData) {
        this.popData = popData;
        this.weatherData = weatherData;
    }



    public String getPopData() {
        return popData;
    }

    public void setPopData(String popData) {
        this.popData = popData;
    }

    public String getWeatherData() {
        return weatherData;
    }

    public void setWeatherData(String weatherData) {
        this.weatherData = weatherData;
    }

    public String getEmploymentData() {
        return EmploymentData;
    }

    public void setEmploymentData(String employmentData) {
        EmploymentData = employmentData;
    }

    public String getPoliticalData() {
        return PoliticalData;
    }

    public void setPoliticalData(String politicalData) {
        PoliticalData = politicalData;
    }
}
