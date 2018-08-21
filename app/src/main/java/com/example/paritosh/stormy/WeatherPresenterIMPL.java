package com.example.paritosh.stormy;

import com.example.paritosh.stormy.model.CurrentWeather;
import com.example.paritosh.stormy.model.CurrentWeatherDataBindingModel;

public class WeatherPresenterIMPL implements WeatherContract.WeatherPresenter {
    private WeatherContract.WeatherView weatherView;


    WeatherPresenterIMPL(WeatherContract.WeatherView weatherView) {
        this.weatherView = weatherView;

    }

    private WeatherDataProvider weatherDataProvider = new WeatherDataProvider();

    @Override
    public void updateWeatherDetails() {
        if (Utils.isNetworkAvailable(weatherView.getContext())) {
            weatherDataProvider.getCurrentWeather(new WeatherDataProvider.OnWeatherApiResponse() {
                @Override
                public void onSuccess(CurrentWeather weather) {
                    CurrentWeatherDataBindingModel model = Utils.convertCurrentWeatherToDataBindingModel(weather);
                    weatherView.render(model);
                }

                @Override
                public void onError(Throwable t) {
                    weatherView.showApiFailError();
                }
            });
        } else {
            weatherView.showConnectivityError();
        }
    }

    @Override
    public void refreshWeatherData() {
        updateWeatherDetails();
        weatherView.showMessage(R.string.refresh_data_message);
    }


}
