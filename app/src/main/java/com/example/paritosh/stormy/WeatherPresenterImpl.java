package com.example.paritosh.stormy;

import com.example.paritosh.stormy.model.CurrentWeather;
import com.example.paritosh.stormy.model.CurrentWeatherDataBindingModel;
import com.example.paritosh.stormy.model.Hourly;

public class WeatherPresenterImpl implements WeatherContract.WeatherPresenter {


    private WeatherContract.WeatherView view;
    static Hourly[] hourly;


    WeatherPresenterImpl(WeatherContract.WeatherView view) {
        this.view = view;

    }

    private WeatherDataProvider weatherDataProvider = new WeatherDataProvider();

    @Override
    public void updateWeatherDetails() {
        if (Utils.isNetworkAvailable(view.loadContext())) {
            weatherDataProvider.getCurrentWeather(new WeatherDataProvider.OnWeatherApiResponse() {
                @Override
                public void onSuccess(CurrentWeather weather, Hourly[] hourly) {
                    CurrentWeatherDataBindingModel model = Utils.convertCurrentWeatherToDataBindingModel(weather);
                    view.render(model);
                    view.loadHourlyData(hourly);
                }

                @Override
                public void onError(Throwable t) {
                    view.showErrorMessage(R.string.error_title, R.string.error_message);
                }
            });
        } else {
            view.showErrorMessage(R.string.error_title, R.string.connectivity_error_message);
        }
    }

    @Override
    public void refreshWeatherData() {
        updateWeatherDetails();
        view.showMessage(R.string.refresh_data_message);
    }


}
