package com.example.harjoitustyo;

public class DataObject {

    private String popData;
    private String weatherDescriptionData;
    private String employmentData;
    private String politicalData;

    private String weatherWindData;

    private String weatherHumidityData;

    private String weatherTempData;

    private String weatherIconData;

    private String jobS;

    public DataObject(String popData, String weatherDescriptionData, String employmentData, String weatherWindData, String weatherHumidityData, String weatherTempData, String weatherIconData, String jobS) {
        this.popData = popData;
        this.weatherDescriptionData = weatherDescriptionData;
        this.employmentData = employmentData;
        this.weatherWindData = weatherWindData;
        this.weatherHumidityData = weatherHumidityData;
        this.weatherTempData = weatherTempData;
        this.weatherIconData = weatherIconData;
        this.jobS = jobS;
    }

    public String getPopData() {
        return popData;
    }

    public void setPopData(String popData) {
        this.popData = popData;
    }

    public String getWeatherDescriptionData() {
        return weatherDescriptionData;
    }

    public void setWeatherDescriptionData(String weatherDescriptionData) {
        this.weatherDescriptionData = weatherDescriptionData;
    }

    public String getEmploymentData() {
        return employmentData;
    }

    public void setEmploymentData(String employmentData) {
        this.employmentData = employmentData;
    }

    public String getPoliticalData() {
        return politicalData;
    }

    public void setPoliticalData(String politicalData) {
        this.politicalData = politicalData;
    }

    public String getWeatherWindData() {
        return weatherWindData;
    }

    public void setWeatherWindData(String weatherWindData) {
        this.weatherWindData = weatherWindData;
    }

    public String getWeatherHumidityData() {
        return weatherHumidityData;
    }

    public void setWeatherHumidityData(String weatherHumidityData) {
        this.weatherHumidityData = weatherHumidityData;
    }

    public String getWeatherTempData() {
        return weatherTempData;
    }

    public void setWeatherTempData(String weatherTempData) {
        this.weatherTempData = weatherTempData;
    }

    public String getWeatherIconData() {
        return weatherIconData;
    }

    public void setWeatherIconData(String weatherIconData) {
        this.weatherIconData = weatherIconData;
    }

    public String getJobS() {
        return jobS;
    }

    public void setJobS(String jobS) {
        this.jobS = jobS;
    }
}
