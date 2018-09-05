package com.example.paritosh.stormy;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.paritosh.stormy.databinding.ActivityMainBinding;
import com.example.paritosh.stormy.model.CurrentWeatherDataBindingModel;
import com.example.paritosh.stormy.model.Hourly;

public class WeatherActivity extends AppCompatActivity implements WeatherContract.WeatherView {

    private WeatherContract.WeatherPresenter presenter = new WeatherPresenterImpl(this);
    private ActivityMainBinding binding;
    private Hourly[] hourlyData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        TextView textView = findViewById(R.id.darkSkyAttribution);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        ImageView refreshImageView = findViewById(R.id.refreshImageView);
        refreshImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.refreshWeatherData();
            }
        });

        presenter.updateWeatherDetails();
        Button hourlyButton = findViewById(R.id.hourly_button);
        hourlyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WeatherActivity.this,
                        HourlyForecast.class);
                Bundle bundle = new Bundle();
                bundle.putParcelableArray("hourlyData", hourlyData);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }

    @Override
    public void render(CurrentWeatherDataBindingModel model) {
        binding.setWeather(model);

    }

    @Override
    public void loadHourlyData(Hourly[] hourlyData) {
        this.hourlyData = hourlyData;
    }

    @Override
    public void showMessage(@StringRes int messageResId) {
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    public void showErrorMessage(int titleResId, int messageResId) {
        SabkaAlertDialogFragment
                .newInstance(titleResId, messageResId)
                .show(getSupportFragmentManager(), "Error dialog");
    }

    @Override
    public Context loadContext() {
        return this;
    }


}
