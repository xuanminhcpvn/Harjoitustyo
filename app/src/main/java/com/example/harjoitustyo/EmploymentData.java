package com.example.harjoitustyo;

public class EmploymentData {

    private int year;
    private double population;

    public EmploymentData(int y, double p) {
        year = y;
        population = p;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getPopulation() {
        return population;
    }

    public void setPopulation(double population) {
        this.population = population;
    }
}
