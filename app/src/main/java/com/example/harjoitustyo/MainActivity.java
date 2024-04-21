package com.example.harjoitustyo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private EditText municipality;



    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });




        // Button for adding, when added ---> tablayout
        // Can be done with onClickListener
        // TODO Also a recyclerview in oncreate

        Button searchButton = findViewById(R.id.searchButton);


       // searchButton.setOnClickListener(listener);
    }

        public void searchMunicipality(View view) {
            municipality = findViewById(R.id.editMunicipality);
            // String municipalityString  = .getText().toString().

            // switch to tabActivity
            Intent intent = new Intent(this, TabActivity.class);



            Log.d("LUT", "Nappula toimii");
            Context context = this;

            PopulationDataRetriever pr = new PopulationDataRetriever();
            WeatherDataRetriever wr = new WeatherDataRetriever();


            String location = municipality.getText().toString();


            ExecutorService service = Executors.newSingleThreadExecutor();

            service.execute(new Runnable() {
                @Override
                public void run() {
                    ArrayList<PopulationData> populationData = pr.getData(context, location);
                    WeatherData weatherData = wr.getWeatherData(location);

                    if (populationData == null) {
                        return;
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            String pop = "";
                            for(PopulationData data : populationData) {
                                pop = pop + data.getYear() + ": " + data.getPopulation() + "\n";
                            }

                            Bundle bundle = new Bundle();
                            bundle.putString("population",pop);
                            bundle.putString("location",location);

                            // txtPopulationData.setText(s);
                            // TODO instead we bundle and send it to Fragment
                                String weather = weatherData.getName() + "\n" +
                                            "Sää nyt: " + weatherData.getMain() + " (" + weatherData.getDescription() +")\n" +
                                            "Lämpötila: " + weatherData.getTemperature() + " K\n" +
                                            "Tuulennopeus: " + weatherData.getWindSpeed() + " m/s\n"
                            ;



                            // We create Bundle here
                            bundle.putString("weatherInfo",weather);

                            // TODO jotta tieto välittyy toiselle activiteetille
                            intent.putExtra("population",pop);
                            intent.putExtra("weatherInfo",weather);


                            // This list is for the list in recycler view
                            // Since the order whhich data are presented are cruciar
                            // remember to check which one to go first
                            // Also out of index error may occur in viewHolder when graphical components > datas in the list
                            // Usually empty one doens't affect but this does (More about how to fix the problem is in the ViewHolder)
                            ArrayList<String> dataList = new ArrayList<>();
                            dataList.add(pop);
                            dataList.add(weather);



                            // intent.putParcelableArrayListExtra("data", (ArrayList <? extends Parcelable>) dataList);
                            // Simply method to send String ArrayList
                            intent.putStringArrayListExtra("dataList", (ArrayList<String>) dataList);

                            startActivity(intent);



                        }

                    });


                    //Log.d("LUT", "Data haettu");
                }
            });


        }

}