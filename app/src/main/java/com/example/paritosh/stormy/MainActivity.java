package com.example.paritosh.stormy;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.paritosh.stormy.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private Presenter presenter = new Presenter();

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
                presenter.refreshWeatherData(binding, MainActivity.this);
            }
        });

        presenter.updateWeatherDetails(binding, this);
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
