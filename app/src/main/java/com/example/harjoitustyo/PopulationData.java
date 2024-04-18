package com.example.harjoitustyo;

public class PopulationData {

        private String name;
        private String main;
        private String description;
        private String population;

        public PopulationData(String n, String m, String d, String p) {
            name = n;
            main = m;
            description = d;
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

        public String getPopulation() {
            return population;
        }

        public void setPopulation(String Population) {
            this.population = population;
        }


}
