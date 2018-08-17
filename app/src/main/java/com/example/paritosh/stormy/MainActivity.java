package com.example.paritosh.stormy;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.paritosh.stormy.databinding.ActivityMainBinding;
import com.example.paritosh.stormy.model.CurrentWeather;
import com.example.paritosh.stormy.model.CurrentWeatherDataBindingModel;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private WeatherDataProvider weatherDataProvider = new WeatherDataProvider();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        TextView textView = findViewById(R.id.darkSkyAttribution);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        ImageView refreshImageView = findViewById(R.id.refreshImageView);
        refreshImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshWeatherData(binding);
            }
        });

        updateWeatherDetails(binding);
    }

    private void updateWeatherDetails(final ActivityMainBinding binding) {
        if (Utils.isNetworkAvailable(this)) {
            weatherDataProvider.getCurrentWeather(new WeatherDataProvider.OnWeatherApiResponse() {
                @Override
                public void onSuccess(CurrentWeather weather) {
                    CurrentWeatherDataBindingModel model = Utils.convertCurrentWeatherToDataBindingModel(weather);
                    binding.setWeather(model);
                }

                @Override
                public void onError(Throwable t) {
                    showApiFailError();
                }
            });
        } else {
            showConnectivityError();
        }
    }


    private void showApiFailError() {
        SabkaAlertDialogFragment.newInstance(
                R.string.error_title,
                R.string.error_message
        ).show(getSupportFragmentManager(), "error dialog");
    }

    private void showConnectivityError() {
        SabkaAlertDialogFragment.newInstance(
                R.string.connection_unavailable_title,
                R.string.connectivity_error_message).
                show(getSupportFragmentManager(), "connectivity error dialog");
    }

    public void refreshWeatherData(ActivityMainBinding binding) {

        Toast.makeText(this, "Refreshing Data", Toast.LENGTH_LONG)
                .show();
        updateWeatherDetails(binding);
    }
}
