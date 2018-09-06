package com.example.paritosh.stormy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.paritosh.stormy.adapter.HourlyForecastAdapter;
import com.example.paritosh.stormy.model.HourlyForecastModel;

import java.util.ArrayList;

public class HourlyForecastActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<HourlyForecastModel> hourlyData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hourly_forecast);
        hourlyData = getIntent().getParcelableArrayListExtra("hourlyData");
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(new HourlyForecastAdapter(hourlyData));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}
