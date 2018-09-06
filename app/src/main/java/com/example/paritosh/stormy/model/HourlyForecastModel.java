package com.example.paritosh.stormy.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.DrawableRes;

public class HourlyForecastModel implements Parcelable {

    private @DrawableRes int icon;
    private String time;
    private String summary;
    private double temperature;

    public HourlyForecastModel() {

    }

    protected HourlyForecastModel(Parcel in) {
        icon = in.readInt();
        time = in.readString();
        summary = in.readString();
        temperature = in.readDouble();
    }

    public static final Creator<HourlyForecastModel> CREATOR = new Creator<HourlyForecastModel>() {
        @Override
        public HourlyForecastModel createFromParcel(Parcel in) {
            return new HourlyForecastModel(in);
        }

        @Override
        public HourlyForecastModel[] newArray(int size) {
            return new HourlyForecastModel[size];
        }
    };


    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(icon);
        dest.writeString(time);
        dest.writeString(summary);
        dest.writeDouble(temperature);

    }
}
