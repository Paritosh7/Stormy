package com.example.paritosh.stormy;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.paritosh.stormy.databinding.ActivityMainBinding;
import com.example.paritosh.stormy.model.CurrentWeatherDataBindingModel;

public class WeatherActivity extends AppCompatActivity {

    private WeatherPresenter presenter = new WeatherPresenter(this);
    private ActivityMainBinding binding;

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
    }

    public void render(CurrentWeatherDataBindingModel model) {
        binding.setWeather(model);

    }

    public void showMessage(@StringRes int messageResId) {
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
                .show();
    }


    public void showApiFailError() {
        SabkaAlertDialogFragment.newInstance(
                R.string.error_title,
                R.string.error_message
        ).show(getSupportFragmentManager(), "error dialog");
    }

    public void showConnectivityError() {
        SabkaAlertDialogFragment.newInstance(
                R.string.connection_unavailable_title,
                R.string.connectivity_error_message).
                show(getSupportFragmentManager(), "connectivity error dialog");
    }


}
