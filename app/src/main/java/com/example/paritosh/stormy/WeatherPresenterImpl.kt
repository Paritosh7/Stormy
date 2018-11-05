package com.example.paritosh.stormy

import com.example.paritosh.stormy.model.CurrentWeather

class WeatherPresenterImpl(val view: WeatherContract.WeatherView) : WeatherContract.WeatherPresenter {
    private val weatherDataProvider = WeatherDataProvider()

    override fun updateWeatherDetails() {

        if (Utils.isNetworkAvailable(view.loadContext())) {
            weatherDataProvider.getCurrentWeather(object : WeatherDataProvider.OnWeatherApiResponse {
                override fun onSuccess(weather: CurrentWeather) {
                    val model = Utils.convertWeatherToDataBindingModel(weather)
                    view.render(model)
                }

                override fun onError(t: Throwable) {
                    view.showErrorMessage(R.string.error_title, R.string.error_message)
                }
            })
        } else
            view.showErrorMessage(R.string.error_title, R.string.connectivity_error_message)
    }

    override fun refreshWeatherDetails() {
        updateWeatherDetails()
        view.showMessage(R.string.refresh_data_message)
    }
}