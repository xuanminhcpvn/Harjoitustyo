package com.example.harjoitustyo;

import static android.app.PendingIntent.getActivity;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class InfoRecyclerViewAdapter extends RecyclerView.Adapter<InfoRecyclerViewHolder> {
    private Context context;
    private TabActivity tabActivity;


    // TODO if you want to implement other data node, must create ArrayList for them ? and then
    // TODO add it to the builder
    private ArrayList<WeatherData> weathers = new ArrayList<>();



    public InfoRecyclerViewAdapter(Context context) {
        this.context = context;
    }
    @NonNull
    @Override
    public InfoRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InfoRecyclerViewHolder(LayoutInflater.from(context).inflate(R.layout.info_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull InfoRecyclerViewHolder holder, int position) {
        String populationData = tabActivity.sendPopData();
        String weatherData = tabActivity.sendWeatherData();
        holder.weatherName.setText(populationData);
        holder.weatherMain.setText(weatherData);
        holder.weatherDescription.setText(weathers.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
