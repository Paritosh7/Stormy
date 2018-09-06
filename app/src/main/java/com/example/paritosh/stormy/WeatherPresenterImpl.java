package com.example.paritosh.stormy;

import com.example.paritosh.stormy.model.CurrentWeather;
import com.example.paritosh.stormy.model.CurrentWeatherDataBindingModel;

public class WeatherPresenterImpl implements WeatherContract.WeatherPresenter {


    private WeatherContract.WeatherView view;

    WeatherPresenterImpl(WeatherContract.WeatherView view) {
        this.view = view;

    }

    private WeatherDataProvider weatherDataProvider = new WeatherDataProvider();

    @Override
    public void updateWeatherDetails() {
        if (Utils.isNetworkAvailable(view.loadContext())) {
            weatherDataProvider.getCurrentWeather(new WeatherDataProvider.OnWeatherApiResponse() {
                @Override
                public void onSuccess(CurrentWeather weather) {
                    CurrentWeatherDataBindingModel model = Utils.convertCurrentWeatherToDataBindingModel(weather);
                    view.render(model);
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
