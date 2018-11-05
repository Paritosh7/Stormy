package com.example.paritosh.stormy

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.paritosh.stormy.adapter.HourlyForecastAdapter
import com.example.paritosh.stormy.model.HourlyForecastModel
import java.util.*

class HourlyForecastActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hourly_forecast)
        val hourlyData: ArrayList<HourlyForecastModel> = getIntent()
                .getParcelableArrayListExtra("hourlyData")
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = HourlyForecastAdapter(hourlyData)
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}
