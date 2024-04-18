package com.example.harjoitustyo;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class PopulationDataRetriever {

    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode areas = null;

        try {
            areas = objectMapper.readTree(new URL("https://pxdata.stat.fi/PxWeb/api/v1/fi/Kuntien_avainluvut/uusin/kuntien_avainluvut_viimeisin.px"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<String> keys = new ArrayList<>();
        ArrayList<String> values = new ArrayList<>();

        for (JsonNode node : areas.get("variables").get(1).get("values")) {
            values.add(node.asText());
        }
        for (JsonNode node : areas.get("variables").get(1).get("valueTexts")) {
            keys.add(node.asText());
        }

        HashMap<String, String> municipalityCodes = new HashMap<>();

        for (int i = 0; i < keys.size(); i++) {
            municipalityCodes.put(keys.get(i), values.get(i));
        }

        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Enter the municipality name: ");
            String municipality = sc.nextLine();
            String code = municipalityCodes.get(municipality);

            if (code != null) {
                try {
                    URL url = new URL("https://pxdata.stat.fi/PxWeb/api/v1/fi/Kuntien_avainluvut/uusin/kuntien_avainluvut_viimeisin.px");

                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("POST");
                    con.setRequestProperty("Content-Type", "application/json; utf-8");
                    con.setRequestProperty("Accept", "application/json");
                    con.setDoOutput(true);

                    JsonNode jsonInputString = objectMapper.readTree(new File("query.json"));

                    ((ObjectNode) jsonInputString.get("query").get(0).get("selection")).putArray("values").add(code);

                    byte[] input = objectMapper.writeValueAsBytes(jsonInputString);
                    try (OutputStream os = con.getOutputStream()) {
                        os.write(input, 0, input.length);
                    }

                    try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))) {
                        StringBuilder response = new StringBuilder();
                        String line;
                        while ((line = br.readLine()) != null) {
                            response.append(line.trim());
                        }

                        JsonNode municipalityData = objectMapper.readTree(response.toString());

                        ArrayList<String> years = new ArrayList<>();
                        ArrayList<String> populations = new ArrayList<>();

                        for (JsonNode node : municipalityData.get("dimension").get("Vuosi").get("category").get("label")) {
                            years.add(node.asText());
                        }

                        for (JsonNode node : municipalityData.get("value")) {
                            populations.add(node.asText());
                        }

                        System.out.println("=======================");
                        System.out.println(municipality);
                        System.out.println("-----------------------");

                        for (int i = 0; i < years.size(); i++) {
                            System.out.println(years.get(i) + ": " + populations.get(i));
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Municipality not found.");
            }
        }
    }
}
