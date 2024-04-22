package com.example.harjoitustyo;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SearchRecyclerViewHolder extends RecyclerView.ViewHolder {
    TextView txtSearch;


    public SearchRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        txtSearch = itemView.findViewById(R.id.txtSearch);
    }
}
