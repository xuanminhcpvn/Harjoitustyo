package com.example.harjoitustyo;

public class DataObject {

    private String popData;
    private String weatherData;
    private String employmentData;
    private String politicalData;

    private String jobS;
    public DataObject(String popData, String weatherData, String employmentData, String jobS) {
        this.popData = popData;
        this.weatherData = weatherData;
        this.employmentData = employmentData;
        this.jobS = jobS;
    }


    public String getJobS() {return jobS;}

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
        return employmentData;
    }

    public void setEmploymentData(String employmentData) {
        employmentData = employmentData;
    }

    public String getPoliticalData() {
        return politicalData;
    }

    public void setPoliticalData(String politicalData) {
        politicalData = politicalData;
    }
}
