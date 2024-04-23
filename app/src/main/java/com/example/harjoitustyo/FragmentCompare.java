package com.example.harjoitustyo;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FragmentCompare extends Fragment {

    // TextView group for first dataset
    private TextView txtWeather1, txtPop1, txtPopChange1;
    // TextView group for second dataset
    private TextView txtWeather2, txtPop2, txtPopChange2;

    private ArrayList<CompareObject> compareObjects;

    private String pop,popChange,weather;

    // TextView group for showing result of comparison

    private TextView txtweatherResult, txtPopResult, txtPopChangeResult;

    private Button buttonCompare, buttonSearch;
    private EditText editCompare1, editCompare2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_compare, container, false);


        txtWeather1 = view.findViewById(R.id.txtWeather1);
        txtPop1 = view.findViewById(R.id.txtPop1);
        txtPopChange1 = view.findViewById(R.id.txtPopChange1);

        txtWeather2 = view.findViewById(R.id.txtWeather2);
        txtPop2 = view.findViewById(R.id.txtPop2);
        txtPopChange2 = view.findViewById(R.id.txtPopChange2);

        txtweatherResult = view.findViewById(R.id.txtWeatherResult);
        txtPopResult = view.findViewById(R.id.txtPopResult);
        txtPopChangeResult = view.findViewById(R.id.txtPopChangeResult);

        // TODO vaihda näiden paikka
        editCompare1 = view.findViewById(R.id.editCompare1);
        editCompare2 = view.findViewById(R.id.editCompare2);

        buttonSearch = view.findViewById(R.id.buttonSearch);
        buttonCompare = view.findViewById(R.id.buttonCompare);

        buttonSearch.setOnClickListener(v -> Search2Data());

        buttonCompare.setOnClickListener(v -> compareAndDisplay() );


        return view;
    }

    private void Search2Data() {
        Context context = getContext();
        String editCompareStr1 = editCompare1.getText().toString();
        String editCompareStr2 = editCompare2.getText().toString();

        PopulationDataRetriever mr = new PopulationDataRetriever();
        WeatherDataRetriever wr = new WeatherDataRetriever();
        ExecutorService service = Executors.newFixedThreadPool(2);


        // Future<ArrayList<String>> future1 =service.submit

                service.execute(() -> fetchData(context, editCompareStr1, txtWeather1, txtPop1, txtPopChange1, mr, wr));


        //Future<ArrayList<String>> future2 = service.submit(() ->
               service.execute(() -> fetchData(context, editCompareStr2, txtWeather2, txtPop2, txtPopChange2, mr, wr));

        service.shutdown();

        Log.d("LUT", txtWeather2.getText().toString());

    }


    private void fetchData(Context context, String municipality, TextView weatherView, TextView popView, TextView popChangeView,
                                        PopulationDataRetriever mr, WeatherDataRetriever wr) {
        ArrayList<PopulationData> populationData = mr.getData(context, municipality, 0);
        ArrayList<PopulationData> populationChangeData = mr.getData(context, municipality, 1);
        WeatherData weatherData = wr.getWeatherData(municipality);
        // agian resourceID 0 is for pop and 1 is for popChange;


        getActivity().runOnUiThread(() -> {

            pop = "";
            popChange = "";

            for (PopulationData data1 : populationChangeData) {
                if (data1.getYear() == 2022) {
                    if (data1.getPopulation() < 0) {
                        popChange = "-" + data1.getPopulation();
                        break;
                    } else {
                        popChange = "+" + data1.getPopulation();
                        break;
                    }
                }
            }




            for (PopulationData data2 : populationData) {
                if (data2.getYear() == 2022) {
                    pop = pop + data2.getPopulation();
                    break;
                }
            }



            weather = "Sää nyt: " + weatherData.getMain() + " (" + weatherData.getDescription() + ")\n" +
                    "Lämpötila: " + weatherData.getTemperature() + " °C\n" +
                    "Tuulennopeus: " + weatherData.getWindSpeed() + " m/s\n" +
                    "Kosteus: " + weatherData.getHumidity() + "%\n";


            popChangeView.setText(popChange);
            popView.setText(pop);
            weatherView.setText(weather);


        });

    }

    private String compareWeather(String weather1, String weather2) {

        if (weather1.equals(weather2)) {
            return "Weather is the same for both.";
        } else {
            return "Weather is different.";
        }
    }

    private String comparePopulation(String pop1, String pop2) {

        int population1 = 0;
        int population2 = 0;

        if (!pop1.isEmpty()){
            population1 = Integer.parseInt(pop1);
        }

        if (!pop2.isEmpty()) {
            population2 = Integer.parseInt(pop2);
        }



        if (population1 == population2) {
            return "Population is the same for both.";
        } else if (population1 > population2) {
            return "Population 1 is greater.";
        } else {
            return "Population 2 is greater.";
        }
    }

    private String comparePopulationChange(String popChange1, String popChange2) {


        int change1 = 0;
        int change2 = 0;

        if (!popChange1.isEmpty()){
            change1 = Integer.parseInt(popChange1);
        }

        if (!popChange2.isEmpty()) {
            change2 = Integer.parseInt(popChange2);
        }
        if (change1 == change2) {
            return "Population change is the same for both.";
        } else if (change1 > change2) {
            return "Population change for 1 is greater.";
        } else {
            return "Population change for 2 is greater.";
        }
    }



    private void compareAndDisplay( ) {

            String weather1 = txtWeather1.getText().toString();
            String weather2 = txtWeather2.getText().toString();
            String pop1 = txtPop1.getText().toString();
            String pop2 = txtPop2.getText().toString();
            String popChange1 = txtPopChange1.getText().toString();
            String popChange2 = txtPopChange2.getText().toString();
            String weatherResult = compareWeather(weather1, weather2);
            String popResult = comparePopulation(pop1, pop2);
            String popChangeResult = comparePopulationChange(popChange1, popChange2);
            Log.d("Ongelma", "data haettu");
            txtweatherResult.setText(weatherResult);
            txtPopResult.setText(popResult);
            txtPopChangeResult.setText(popChangeResult);
    }

}





