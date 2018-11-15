package com.example.paritosh.stormy

import android.content.Context
import android.net.ConnectivityManager
import com.example.paritosh.stormy.model.CurrentWeather
import com.example.paritosh.stormy.model.CurrentWeatherDataBindingModel
import java.text.SimpleDateFormat
import java.util.*

class Utils {

    companion object {
        fun isNetworkAvailable(context: Context): Boolean {
            val connectivityManager: ConnectivityManager? =
                    context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            return connectivityManager?.activeNetworkInfo?.isConnected ?: false
        }

        fun convertWeatherToDataBindingModel(currentWeather: CurrentWeather) = CurrentWeatherDataBindingModel(
                currentWeather.locationLabel,
                getIconId(currentWeather.icon),
                getReadableTime(currentWeather.time, currentWeather.locationLabel),
                currentWeather.temperature, currentWeather.humidity, currentWeather.precipLevel,
                currentWeather.summary, currentWeather.hourly
        )

        fun getReadableTime(time: Long, locationLabel: String): String {
            val simpleDateFormat = SimpleDateFormat("h:mm a", Locale.getDefault())
            simpleDateFormat.timeZone = TimeZone.getTimeZone(locationLabel)

            val dateTime = Date(time * 1000L)

            return simpleDateFormat.format(dateTime)
        }

        fun getIconId(icon: String) = when (icon) {
            "rain" -> R.drawable.rain
            "snow" -> R.drawable.snow
            "sleet" -> R.drawable.sleet
            "wind" -> R.drawable.wind
            "fog" -> R.drawable.fog
            "cloudy" -> R.drawable.cloudy
            "partly-cloudy-day" -> R.drawable.partly_cloudy
            "partly-cloudy-night" -> R.drawable.clear_night
            else -> R.drawable.clear_day
        }
    }

}
