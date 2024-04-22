package com.example.harjoitustyo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SearchRecyclerViewAdapter extends RecyclerView.Adapter<SearchRecyclerViewHolder> implements Activity {

    private Context context;
    private ArrayList<Search> searches;

    private String cityName;

    private OnItemClickListener listener;


    public SearchRecyclerViewAdapter(Context context, ArrayList<Search> searches, OnItemClickListener listener) {
        this.context = context;
        this.searches = searches;
        this.listener = listener;
    }

    @NonNull
    @Override
    public SearchRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SearchRecyclerViewHolder((LayoutInflater.from(context).inflate(R.layout.search_view, parent, false)));
    }

    @Override
    public void onItemClick(String cityName) {

    }

    // Credit to Samym Mohibby on how to make ArrayList item connects with the Fragment
    public interface OnItemClickListener {

        void onItemClick(String cityName);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchRecyclerViewHolder holder, int position) {
        Search search = searches.get(position);
        holder.txtSearch.setText(searches.get(position).getCityName());

        // Use the custom onItemClick method on the main activity not the default provided by Android
        holder.itemView.setOnClickListener(v -> listener.onItemClick(search.getCityName()));
    }

    @Override
    public int getItemCount() {
        return searches.size();
    }
}
