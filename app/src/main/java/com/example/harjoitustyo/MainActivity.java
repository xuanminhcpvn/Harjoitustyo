package com.example.harjoitustyo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity implements SearchRecyclerViewAdapter.OnItemClickListener, Activity {

    private EditText municipality;

    private View view;
    private String location;
    private String searchTerm;

    private RecyclerView.Adapter adapter;

    private RecyclerView rvSearches;
    private SearchList searchesStorage;



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
        municipality = findViewById(R.id.editMunicipality);
        rvSearches = findViewById(R.id.rvSearch);
        updateSearches();


       // searchButton.setOnClickListener(listener);
    }




    public void searchMunicipality(View v) {

            // String municipalityString  = .getText().toString().

            // switch to tabActivity
            Intent intent = new Intent(this, TabActivity.class);


            Log.d("LUT", "Nappula toimii");
            Context context = this;

            PopulationDataRetriever pr = new PopulationDataRetriever();
            WeatherDataRetriever wr = new WeatherDataRetriever();
            EmploymentDataRetriever er = new EmploymentDataRetriever();

            location = municipality.getText().toString();


            ExecutorService service = Executors.newSingleThreadExecutor();

            service.execute(new Runnable() {
                @Override
                public void run() {


                    // 0 and 1 define what R.raw resource we take since population ja populationChange are almost identical
                    ArrayList<PopulationData> populationData = pr.getData(context, location, 0);
                    ArrayList<PopulationData> populationChangeData = pr.getData(context, location, 1);
                    ArrayList<EmploymentData> employmentData = er.getData(context, location);
                    WeatherData weatherData = wr.getWeatherData(location);

                    if (populationData == null) {
                        return;
                    }

                    if (populationChangeData == null) {
                        return;
                    }

                    if (employmentData == null) {
                        return;
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            String pop = "";
                            String popChange = "";
                            String emp = "";

                            for (PopulationData data1 : populationChangeData) {
                                if (data1.getYear() == 2022) {
                                    if (data1.getPopulation() < 0) {
                                        popChange = popChange + "-" + data1.getPopulation();
                                        break;
                                    } else {
                                        popChange = popChange + "+" + data1.getPopulation();
                                        break;
                                    }
                                }
                            }


                            for (PopulationData data2 : populationData) {
                                if (data2.getYear() == 2022) {
                                    pop = pop + "Population " + data2.getYear() + ": " + data2.getPopulation() + "(" + popChange + ")" + "\n";
                                    break;
                                }
                            }

                            for (EmploymentData data3 : employmentData) {
                                if (data3.getYear() == 2022) {
                                    emp = "Employment rate of year " + data3.getYear() + ": " + data3.getPopulation() + "%\n";
                                    break;
                                }
                            }


                            // txtPopulationData.setText(s);
                            // TODO instead we bundle and send it to Fragment
                            String weather = "Sää nyt: " + weatherData.getMain() + " (" + weatherData.getDescription() + ")\n" +
                                    "Lämpötila: " + weatherData.getTemperature() + " °C\n" +
                                    "Tuulennopeus: " + weatherData.getWindSpeed() + " m/s\n" +
                                    "Kosteus: " + weatherData.getHumidity() + "%\n";

                            String icon  = weatherData.getIcon();


                            Bundle bundle = new Bundle();
                            intent.putExtra("iconNumber", icon);
                            // TODO jotta tieto välittyy toiselle activiteetille
                            intent.putExtra("population", pop);
                            intent.putExtra("weatherInfo", weather);
                            intent.putExtra("location", location);
                            intent.putExtra("EmploymentRate", emp);


                            // This list is for the list in recycler view
                            // Since the order whhich data are presented are cruciar
                            // remember to check which one to go first
                            // Also out of index error may occur in viewHolder when graphical components > datas in the list
                            // Usually empty one doens't affect but this does (More about how to fix the problem is in the ViewHolder)
                            ArrayList<String> dataList = new ArrayList<>();
                            dataList.add(pop);
                            dataList.add(weather);
                            dataList.add(emp);
                            // intent.putParcelableArrayListExtra("data", (ArrayList <? extends Parcelable>) dataList);
                            // Simply method to send String ArrayList
                            intent.putStringArrayListExtra("dataList", (ArrayList<String>) dataList);

                            startActivity(intent);
                        }

                    });


                    //Log.d("LUT", "Data haettu");
                }

                ;
            });

            searchTerm = municipality.getText().toString();
            if (!searchTerm.isEmpty()) {
                ArrayList<Search> searches = SearchList.getInstance().getSearches();
                for (Search s: searches) {
                    if (searches.size() > 5) {
                        searches.remove(-1);
                    }
                    if (s.getCityName().equals(searchTerm) ){
                        break;
                    } else {
                        Search search = new Search(location);
                        SearchList.getInstance().addSearch(search);
                        }
                }


                // Search search = new Search(location);
                // SearchList.getInstance().addSearch(search);
                updateSearches();

            } else {
                municipality.setError("Please enter a search term");
            }
        }


    private void updateSearches() {
        if (rvSearches != null ) {
            searchesStorage = SearchList.getInstance();
            rvSearches.setLayoutManager(new LinearLayoutManager(this));
            adapter = new SearchRecyclerViewAdapter(this, searchesStorage.getSearches(), this);
            rvSearches.setAdapter(adapter);

        }
    }
    @Override
    public void onItemClick(String cityName) {
        location = cityName;
        searchTerm = cityName;

        searchMunicipality(view);
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }



}