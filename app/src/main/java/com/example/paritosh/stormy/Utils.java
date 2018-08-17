package com.example.paritosh.stormy;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.paritosh.stormy.model.CurrentWeather;
import com.example.paritosh.stormy.model.CurrentWeatherDataBindingModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Utils {
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (connectivityManager != null) {
            networkInfo = connectivityManager.getActiveNetworkInfo();
        }

        Boolean isAvailable = false;

        if (networkInfo != null && networkInfo.isConnected()) {
            isAvailable = true;
        }

        return isAvailable;
    }

    public static CurrentWeatherDataBindingModel convertCurrentWeatherToDataBindingModel(CurrentWeather currentWeather) {
        CurrentWeatherDataBindingModel dataBindingModel = new CurrentWeatherDataBindingModel();

        dataBindingModel.setHumidity(currentWeather.getHumidity());
        dataBindingModel.setLocationLabel(currentWeather.getLocationLabel());
        dataBindingModel.setPrecipChance(currentWeather.getPrecipChance());
        dataBindingModel.setSummary(currentWeather.getSummary());
        dataBindingModel.setTemperature(currentWeather.getTemperature());
        dataBindingModel.setIconRes(getIconId(currentWeather.getIcon()));
        dataBindingModel.setTimeString(getReadableTime(currentWeather.getTime(), currentWeather.getLocationLabel()));

        return dataBindingModel;
    }

    private static int getIconId(String icon) {

        //clear-day, clear-night, rain, snow, sleet, wind,
        // fog, cloudy, partly-cloudy-day, or partly-cloudy-night

        int iconId = R.drawable.clear_day;

        switch (icon) {

            case "rain":
                iconId = R.drawable.rain;
                break;
            case "snow":
                iconId = R.drawable.snow;
                break;
            case "sleet":
                iconId = R.drawable.sleet;
                break;
            case "wind":
                iconId = R.drawable.wind;
                break;
            case "fog":
                iconId = R.drawable.fog;
                break;
            case "cloudy":
                iconId = R.drawable.cloudy;
                break;
            case "partly-cloudy-day":
                iconId = R.drawable.partly_cloudy;
                break;
            case "partly-cloudy-night":
                iconId = R.drawable.cloudy_night;
                break;
        }

        return iconId;
    }

    private static String getReadableTime(long time, String locationLabel) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("h:mm a", Locale.getDefault());
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(locationLabel));

        Date dateTime = new Date(time * 1000L);


        return simpleDateFormat.format(dateTime);
    }
}
