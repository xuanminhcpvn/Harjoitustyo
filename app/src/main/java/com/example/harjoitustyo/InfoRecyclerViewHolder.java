package com.example.harjoitustyo;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class InfoRecyclerViewHolder extends RecyclerView.ViewHolder {

// TODO ALL textView and ImageView Component here

    TextView weatherName, weatherMain, weatherDescription, weatherTemp, weatherWind;

    ImageView imageWeather;



    public InfoRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        weatherName = itemView.findViewById(R.id.weatherName);
        weatherMain = itemView.findViewById(R.id.weatherMain);
        weatherDescription = itemView.findViewById(R.id.weatherDescription);
        weatherWind = itemView.findViewById(R.id.weatherWind);
        weatherTemp = itemView.findViewById(R.id.weatherTemp);
        imageWeather = itemView.findViewById(R.id.imageWeather);

    }
}
