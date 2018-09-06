package com.example.paritosh.stormy.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.paritosh.stormy.R;
import com.example.paritosh.stormy.model.HourlyForecastModel;

import java.util.ArrayList;

public class HourlyForecastAdapter extends RecyclerView.Adapter<HourlyForecastAdapter.HourlyForecastViewHolder> {

    private ArrayList<HourlyForecastModel> hourly;

    public HourlyForecastAdapter(ArrayList<HourlyForecastModel> hourly) {
        this.hourly = hourly;
    }

    @NonNull
    @Override
    public HourlyForecastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new HourlyForecastViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.hourly_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HourlyForecastViewHolder holder, int position) {


        holder.iconImage.setImageResource(hourly.get(position).getIcon());
        holder.summaryText.setText(hourly.get(position).getSummary());
        holder.temperatureText.setText(hourly.get(position).getTemperature() + "");
        holder.timeText.setText(hourly.get(position).getTime());

    }

    @Override
    public int getItemCount() {
        return hourly.size();
    }

    class HourlyForecastViewHolder extends RecyclerView.ViewHolder {

        TextView timeText, temperatureText, summaryText;
        ImageView iconImage;

        public HourlyForecastViewHolder(View itemView) {
            super(itemView);
            timeText = itemView.findViewById(R.id.timeText);
            temperatureText = itemView.findViewById(R.id.temperatureText);
            summaryText = itemView.findViewById(R.id.summaryText);
            iconImage = itemView.findViewById(R.id.iconImage);

        }
    }
}
