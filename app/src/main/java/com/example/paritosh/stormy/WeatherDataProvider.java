package com.example.paritosh.stormy;

import android.support.annotation.NonNull;

import com.example.paritosh.stormy.model.CurrentWeather;
import com.example.paritosh.stormy.model.HourlyForecastModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WeatherDataProvider {

    private String apiKey = "cde93abb7a85053efe6dcd43de69e1fa";
    private double longitude = 42.3601;
    private double latitude = -71.0589;

    private String forecastURL = "https://api.darksky.net/forecast/"
            + apiKey + "/" + longitude + "," + latitude;

    public void getCurrentWeather(final OnWeatherApiResponse onWeatherApiResponse) {

        OkHttpClient client = new OkHttpClient();

        // Builder Pattern
        Request request = new Request.Builder()
                .url(forecastURL)
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                onWeatherApiResponse.onError(e);
            }

            @Override
            public void onResponse(@NonNull Call call, Response response) throws IOException {

                String jsonData = response.body().string();

                try {
                    onWeatherApiResponse.onSuccess(parseCurrentWeather(jsonData));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private CurrentWeather parseCurrentWeather(String jsonData) throws JSONException {

        JSONObject jsonObject = new JSONObject(jsonData);
        CurrentWeather currentWeather = new CurrentWeather();

        currentWeather.setLocationLabel(jsonObject.getString("timezone"));

        JSONObject currently = jsonObject.getJSONObject("currently");

        currentWeather.setTime(currently.getLong("time"));
        currentWeather.setIcon(currently.getString("icon"));
        currentWeather.setTemperature(currently.getDouble("temperature"));
        currentWeather.setHumidity(currently.getDouble("humidity"));
        currentWeather.setPrecipChance(currently.getDouble("precipProbability"));
        currentWeather.setSummary(currently.getString("summary"));

        ArrayList<HourlyForecastModel> hourly = new ArrayList<>();

        JSONObject hourlyObject = jsonObject.getJSONObject("hourly");
        JSONArray hourlyData = hourlyObject.getJSONArray("data");
        for (int i = 0; i < hourlyData.length(); i++) {
            HourlyForecastModel hour = new HourlyForecastModel();
            JSONObject data = hourlyData.getJSONObject(i);

            hour.setSummary(data.getString("summary"));
            hour.setTemperature(data.getDouble("temperature"));
            hour.setIcon(Utils.getIconId(data.getString("icon")));
            hour.setTime(Utils.getReadableTime(data.getLong("time"), currentWeather.getLocationLabel()));

            hourly.add(i, hour);
        }
        currentWeather.setHourly(hourly);
        return currentWeather;

    }

    public interface OnWeatherApiResponse {
        void onSuccess(CurrentWeather weather);

        void onError(Throwable t);
    }
}
