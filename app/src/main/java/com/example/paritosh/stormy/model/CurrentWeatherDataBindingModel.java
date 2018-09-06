package com.example.paritosh.stormy.model;

import android.support.annotation.DrawableRes;

import java.util.ArrayList;

public class CurrentWeatherDataBindingModel {

    private String locationLabel;
    @DrawableRes private int iconRes;
    private String timeString;
    private double temperature;
    private double humidity;
    private double precipChance;
    private String summary;
    private ArrayList<HourlyForecastModel> hourly;


    public CurrentWeatherDataBindingModel() {

    }


    public String getLocationLabel() {
        return locationLabel;
    }

    public void setLocationLabel(String locationLabel) {
        this.locationLabel = locationLabel;
    }

    public int getIconRes() {
        return iconRes;
    }

    public void setIconRes(int iconRes) {
        this.iconRes = iconRes;
    }

    public String getTimeString() {
        return timeString;
    }

    public void setTimeString(String timeString) {
        this.timeString = timeString;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getPrecipChance() {
        return precipChance;
    }

    public void setPrecipChance(double precipChance) {
        this.precipChance = precipChance;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public ArrayList<HourlyForecastModel> getHourly() {
        return hourly;
    }

    public void setHourly(ArrayList<HourlyForecastModel> hourly) {
        this.hourly = hourly;
    }
}
