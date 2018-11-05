package com.example.paritosh.stormy.model

import java.util.ArrayList

data class CurrentWeatherDataBindingModel(
        val locationLabel: String, val iconRes: Int,
        val timeString: String, val temperature: Double,
        val humidity: Double, val precipChance: Double,
        val summary: String, val hourly: ArrayList<HourlyForecastModel>
)