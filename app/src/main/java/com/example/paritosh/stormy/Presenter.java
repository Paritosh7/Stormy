package com.example.paritosh.stormy;

import android.content.Context;
import android.widget.Toast;

import com.example.paritosh.stormy.databinding.ActivityMainBinding;
import com.example.paritosh.stormy.model.CurrentWeather;
import com.example.paritosh.stormy.model.CurrentWeatherDataBindingModel;

public class Presenter {


    private WeatherDataProvider weatherDataProvider = new WeatherDataProvider();

    public void updateWeatherDetails(final ActivityMainBinding binding, Context context) {
        final MainActivity mainActivity = (MainActivity) context;
        if (Utils.isNetworkAvailable(context)) {
            weatherDataProvider.getCurrentWeather(new WeatherDataProvider.OnWeatherApiResponse() {
                @Override
                public void onSuccess(CurrentWeather weather) {
                    CurrentWeatherDataBindingModel model = Utils.convertCurrentWeatherToDataBindingModel(weather);
                    binding.setWeather(model);
                }

                @Override
                public void onError(Throwable t) {
                    mainActivity.showApiFailError();
                }
            });
        } else {
            mainActivity.showConnectivityError();
        }
    }

    public void refreshWeatherData(ActivityMainBinding binding, Context context) {

        Toast.makeText(context, "Refreshing Data", Toast.LENGTH_LONG)
                .show();
        updateWeatherDetails(binding, context);
    }
}
