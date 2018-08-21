package com.example.paritosh.stormy;


import android.content.Context;
import android.support.annotation.StringRes;

import com.example.paritosh.stormy.model.CurrentWeatherDataBindingModel;

public interface WeatherContract {

    interface WeatherView {

        void render(CurrentWeatherDataBindingModel model);

        void showMessage(@StringRes int messageResId);

        void showErrorMessage(@StringRes int titleResId, @StringRes int messageResId);

        Context loadContext();


    }

    interface WeatherPresenter {
        void updateWeatherDetails();

        void refreshWeatherData();

    }


}
