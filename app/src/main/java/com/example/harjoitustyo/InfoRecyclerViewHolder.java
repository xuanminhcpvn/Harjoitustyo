package com.example.harjoitustyo;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class InfoRecyclerViewHolder extends RecyclerView.ViewHolder {

// TODO ALL textView and ImageView Component here

    // TextView textPop, textEmploy, weatherName, weatherMain, weatherDescription, weatherTemp, weatherWind;

    // ImageView imageWeather;

    TextView textPop,  textEmploy, textJobS;

    TextView weatherDescription, weatherTemp, weatherHumidity, weatherWind;


    // TODO this is important in order to not get outofindex error only add graphical components
    // TODO --> has value, extra one should be commented out

    public InfoRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);

        textPop = itemView.findViewById(R.id.textPop);
        textEmploy = itemView.findViewById(R.id.textEmploy);
        textJobS = itemView.findViewById(R.id.textJobS);
        weatherDescription = itemView.findViewById(R.id.weatherDescription);
        weatherTemp = itemView.findViewById(R.id.weatherTemp);
        weatherHumidity = itemView.findViewById(R.id.weatherHumidity);
        weatherWind = itemView.findViewById(R.id.weatherWind);
        // imageWeather = itemView.findViewById(R.id.imageWeather);

    }
}
