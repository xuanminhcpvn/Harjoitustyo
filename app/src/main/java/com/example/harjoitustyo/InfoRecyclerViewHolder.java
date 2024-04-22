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

    TextView textPop, weatherDescription, textEmploy;


    // TODO this is important in order to not get outofindex error only add graphical components
    // TODO --> has value, extra one should be commented out

    public InfoRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        // textPop = itemView.findViewById(R.id.textPop);
        textPop = itemView.findViewById(R.id.textPop);
        weatherDescription = itemView.findViewById(R.id.weatherDescription);
        textEmploy = itemView.findViewById(R.id.textEmploy);
        // weatherDescription = itemView.findViewById(R.id.weatherDescription);
        // weatherWind = itemView.findViewById(R.id.weatherWind);
        // weatherTemp = itemView.findViewById(R.id.weatherTemp);
        // imageWeather = itemView.findViewById(R.id.imageWeather);

    }
}
