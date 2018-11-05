package com.example.paritosh.stormy.model

data class CurrentWeather(
        val locationLabel: String, val icon: String, val time: Long
        , val temperature: Double, val humidity: Double
        , val precipLevel: Double, val summary: String,
        val hourly: ArrayList<HourlyForecastModel>
)
