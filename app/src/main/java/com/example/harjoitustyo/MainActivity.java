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

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity implements SearchRecyclerViewAdapter.OnItemClickListener, Activity {

    private EditText municipality;
    private View view;
    private String location;
    private String searchTerm;

    private RecyclerView.Adapter adapter;

    private DataList dataList;

    private RecyclerView rvSearches;
    private SearchList searchesStorage;

    // All datas are fetch in main activity
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

        Button searchButton = findViewById(R.id.searchButton);
        municipality = findViewById(R.id.editMunicipality);
        rvSearches = findViewById(R.id.rvSearch);
        updateSearches();



    }




    public void searchMunicipality(View v) {

            // String municipalityString  = .getText().toString().

            // switch to tabActivity

            location = municipality.getText().toString();
            Intent intent = new Intent(this, TabActivity.class);


            Log.d("LUT", "Nappula toimii");

            searchTerm = municipality.getText().toString();
            if (!searchTerm.isEmpty()) {
            ArrayList<Search> searches = SearchList.getInstance().getSearches();
            for (Search s: searches) {
                if (searches.size() > 5) {
                    searches.remove(-1);
                }
                if (!s.getCityName().equals(location)){
                    Log.d("Added into the List","Added into the list");
                    Search search = new Search(location);
                    SearchList.getInstance().addSearch(search);
                }
            }
                Search search = new Search(location);
                SearchList.getInstance().addSearch(search);

            updateSearches();
            } else {
                municipality.setError("Please enter a search term");
            }

            Context context = this;
            PopulationDataRetriever pr = new PopulationDataRetriever();
            WeatherDataRetriever wr = new WeatherDataRetriever();
            EmploymentDataRetriever er = new EmploymentDataRetriever();
            JobSelfSuffienciencyRetriever jr = new JobSelfSuffienciencyRetriever();

            ExecutorService service = Executors.newSingleThreadExecutor();

            service.execute(new Runnable() {
                @Override
                public void run() {


                    // 0 and 1 define what R.raw resource we take since population ja populationChange are almost identical
                    ArrayList<PopulationData> populationData = pr.getData(context, location, 0);
                    ArrayList<PopulationData> populationChangeData = pr.getData(context, location, 1);
                    ArrayList<EmploymentData> employmentData = er.getData(context, location);
                    ArrayList<JobSelfSufficiency> jobData = jr.getData(context,location);
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
                            String jobS = "";

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

                            for ( JobSelfSufficiency data4 : jobData) {
                                if (data4.getYear() == 2022) {
                                    jobS = "Job self-suffieciency rate of year " + data4.getYear() +": "+data4.getPopulation() + "%\n";
                                }
                            }


                            // txtPopulationData.setText(s);
                            // TODO instead we bundle and send it to Fragment
                            String weatherDescription = "Sää nyt: " + weatherData.getMain() + " (" + weatherData.getDescription() + ")\n";

                            String weatherTemp = weatherData.getTemperature() + " °C\n";
                            String weatherWind = "Tuulennopeus: " + weatherData.getWindSpeed() + " m/s\n";
                            String weatherHumidity = "Kosteus: " + weatherData.getHumidity() + "%\n";
                            String icon  = weatherData.getIcon();

                            DataObject data = new DataObject(pop,weatherDescription,emp,weatherWind,weatherHumidity,weatherTemp,icon,jobS);
                            dataList.getInstance().addDataObject(data);

                            Bundle bundle = new Bundle();
                            intent.putExtra("jobS",jobS);
                            intent.putExtra("popChange Number", pop);
                            intent.putExtra("iconNumber", icon);
                            // TODO jotta tieto välittyy toiselle activiteetille
                            intent.putExtra("population", pop);
                            intent.putExtra("weatherTemp", weatherTemp);
                            intent.putExtra("weatherWind", weatherWind);
                            intent.putExtra("weatherHumidity", weatherHumidity);
                            intent.putExtra("weatherDescription", weatherDescription);
                            intent.putExtra("location", location);
                            intent.putExtra("EmploymentRate", emp);

                            startActivity(intent);

                        }

                    });


                    Log.d("LUT", "Data haettu");
                }


                ;
            });
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