package com.example.harjoitustyo;

import static android.app.PendingIntent.getActivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class InfoRecyclerViewAdapter extends RecyclerView.Adapter<InfoRecyclerViewHolder> {
    private Context context;
    private TabActivity tabActivity;


    // TODO if you want to implement other data node, must create ArrayList for them ? and then
    // TODO add it to the builder

    private ArrayList<DataObject> datas = new ArrayList<>();


     // So I learned that you need a data structure in RV, hence the need to create separate ArrayList

    public InfoRecyclerViewAdapter(Context context, ArrayList<DataObject> datas) {
        this.context = context;
        this.datas = datas;
    }
    @NonNull
    @Override
    public InfoRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InfoRecyclerViewHolder(LayoutInflater.from(context).inflate(R.layout.info_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull InfoRecyclerViewHolder holder, int position) {
        holder.textPop.setText(datas.get(position).getPopData());
        holder.textEmploy.setText(datas.get(position).getEmploymentData());
        holder.textJobS.setText(datas.get(position).getJobS());
        holder.weatherHumidity.setText(datas.get(position).getWeatherHumidityData());
        holder.weatherTemp.setText(datas.get(position).getWeatherTempData());
        holder.weatherWind.setText(datas.get(position).getWeatherWindData());
        holder.weatherDescription.setText(datas.get(position).getWeatherDescriptionData());

        // holder.weatherDescription.setText(weathers.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }
}
