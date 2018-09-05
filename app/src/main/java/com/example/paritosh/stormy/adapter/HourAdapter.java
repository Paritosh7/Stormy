package com.example.paritosh.stormy.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.paritosh.stormy.R;
import com.example.paritosh.stormy.model.Hourly;

import java.util.ArrayList;

public class HourAdapter extends RecyclerView.Adapter<HourAdapter.HourHolder> {
    LayoutInflater inflater;
    ArrayList<Hourly> hourly;

    public HourAdapter(ArrayList<Hourly> hourly, Context context) {
        inflater = LayoutInflater.from(context);
        this.hourly = hourly;
    }

    @NonNull
    @Override
    public HourHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.hourly_list_item, parent, false);

        return new HourHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HourHolder holder, int position) {


        holder.iconImage.setImageResource(hourly.get(position).getIcon());
        holder.summaryText.setText(hourly.get(position).getSummary());
        holder.temperatureText.setText(hourly.get(position).getTemperature() + "");
        holder.timeText.setText(hourly.get(position).getTime());

    }

    @Override
    public int getItemCount() {
        return hourly.size();
    }

    public class HourHolder extends RecyclerView.ViewHolder {

        TextView timeText, temperatureText, summaryText;
        ImageView iconImage;

        public HourHolder(View itemView) {
            super(itemView);
            timeText = itemView.findViewById(R.id.timeText);
            temperatureText = itemView.findViewById(R.id.temperatureText);
            summaryText = itemView.findViewById(R.id.summaryText);
            iconImage = itemView.findViewById(R.id.iconImage);

        }
    }
}
