package com.example.harjoitustyo;

public class PopulationData {

        private String name;
        private String main;
        private String description;

        private int year;
        private int population;

        public PopulationData(String n, String m, String d, int p) {
            name = n;
            main = m;
            description = d;
            population = p;

        }

        // Muokattu sopivaksi opettajan versioon
        public PopulationData(int y, int p) {
            year = y;
            population = p;
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
