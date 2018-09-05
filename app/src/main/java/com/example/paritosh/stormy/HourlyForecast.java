package com.example.paritosh.stormy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.paritosh.stormy.adapter.HourAdapter;
import com.example.paritosh.stormy.model.Hourly;

public class HourlyForecast extends AppCompatActivity {

    RecyclerView recyclerView;
    Hourly[] hourlyData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hourly_forecast);
        hourlyData = (Hourly[]) getIntent().getExtras().getParcelableArray("hourlyData");


        HourAdapter adapter = new HourAdapter(hourlyData, this);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}
