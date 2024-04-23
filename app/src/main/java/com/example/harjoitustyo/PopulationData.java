package com.example.harjoitustyo;

public class PopulationData {
        private int year;
        private int population;


        public PopulationData(int y, int p) {
            year = y;
            population = p;
        }

        public int getPopulation() {
            return population;
        }

        public void setPopulation(int population) {
            this.population = population;
        }

        public int getYear() {
        return year;
        }
}
