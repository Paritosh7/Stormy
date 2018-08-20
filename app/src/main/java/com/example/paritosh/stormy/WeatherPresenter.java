package com.example.paritosh.stormy;

import com.example.paritosh.stormy.model.CurrentWeather;
import com.example.paritosh.stormy.model.CurrentWeatherDataBindingModel;

public class WeatherPresenter {
    WeatherActivity weatherActivity;


    WeatherPresenter(WeatherActivity weatherActivity) {
        this.weatherActivity = weatherActivity;
    }


    private WeatherDataProvider weatherDataProvider = new WeatherDataProvider();

    public void updateWeatherDetails() {
        if (Utils.isNetworkAvailable(weatherActivity)) {
            weatherDataProvider.getCurrentWeather(new WeatherDataProvider.OnWeatherApiResponse() {
                @Override
                public void onSuccess(CurrentWeather weather) {
                    CurrentWeatherDataBindingModel model = Utils.convertCurrentWeatherToDataBindingModel(weather);
                    weatherActivity.render(model);
                }

                @Override
                public void onError(Throwable t) {
                    weatherActivity.showApiFailError();
                }
            });
        } else {
            weatherActivity.showConnectivityError();
        }
    }


    public void refreshWeatherData() {

        weatherActivity.showMessage(R.string.toast_refreshing_data);

    }


}
