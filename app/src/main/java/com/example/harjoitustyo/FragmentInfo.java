package com.example.harjoitustyo;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentInfo#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentInfo extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ImageView imgWeather;
    private TextView txtLocation, txtPopulation, txtWeather;

    private RecyclerView recyclerView;
    private TabActivity activity;

    private DataList dataList;
    private RecyclerView.Adapter adapter;


    public FragmentInfo() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentInfo.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentInfo newInstance(String param1, String param2) {
        FragmentInfo fragment = new FragmentInfo();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_info, container, false);

        // TODO instead do this https://stackoverflow.com/questions/46050185/android-pass-data-from-activity-to-fragment-in-viewpager

        TabActivity activity = (TabActivity) getActivity();
        String populationData = activity.sendPopData();
        String location = activity.sendLocation();

        String icon = activity.sendIconData();

        txtLocation = view.findViewById(R.id.txtLocation);
        txtLocation.setText(location);



        recyclerView = view.findViewById(R.id.imgWeather);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new InfoRecyclerViewAdapter(getContext(),dataList.getInstance().getDatas());
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

}