package com.example.harjoitustyo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TabActivity extends AppCompatActivity {

    // Define variables first
    private String populationData;

    private String emp, weather, pop, popChange, jobS;
    private String weatherData;
    private String location;

    private String icon;

    private String employmentData;

    private DataList dataList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tab);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        ViewPager2 viewArea = findViewById(R.id.viewArea);
        TabPagerAdapter tabPagerAdapter = new TabPagerAdapter(this);
        viewArea.setAdapter(tabPagerAdapter);


        // TODO Link: https://stackoverflow.com/questions/51917897/explanation-about-intent-getstringextra-and-intent-putextra-in-android-studio
        /* TODO With getIntent().getStringExtra(Bundle Key)--> get information from other Activity
        First have to put data in the original activity(Here it is main) to a Bundle package and
         send it with command putExtra() */
        pop = getIntent().getStringExtra("population");
        weather = getIntent().getStringExtra("weatherInfo");
        location = getIntent().getStringExtra("location");
        emp = getIntent().getStringExtra("EmploymentRate");
        icon = getIntent().getStringExtra("iconNumber");
        jobS = getIntent().getStringExtra("jobS");



        // One user suggest to create yourself sendData() method in activity to fragment
            // Link https://stackoverflow.com/questions/46050185/android-pass-data-from-activity-to-fragment-in-viewpager
        // Method is under tabLayout setup
        // Next go to FragmentInfo


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewArea.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewArea.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabLayout.getTabAt(position).select();
            }
        });
    }



    public String sendLocation(){
        return location;
    }

    public String sendPopData() {
        return pop;

    }

    public String sendPopChangeData() {return popChange; }

    public String sendIconData() {return icon;}

    // in order to return dataList we have to define datalist as private variable

    public String sendJobSData() {return jobS;}

    public String sendWeatherData(){
        return weather;
    }

    public String sendEmploymentData() { return emp;}

}
