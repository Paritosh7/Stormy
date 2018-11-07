package com.example.paritosh.stormy

import android.content.Context
import com.example.paritosh.stormy.model.CurrentWeatherDataBindingModel

interface WeatherContract {

    interface WeatherView {

        fun render(model: CurrentWeatherDataBindingModel)

        fun showMessage(messageResId: Int)

        fun showErrorMessage(titleResId: Int, messageResId: Int)

        fun loadContext(): Context
    }

    interface WeatherPresenter {

        fun updateWeatherDetails()

        fun refreshWeatherDetails()
    }
}