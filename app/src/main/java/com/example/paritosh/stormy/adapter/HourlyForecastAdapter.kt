package com.example.paritosh.stormy.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.paritosh.stormy.R
import com.example.paritosh.stormy.model.HourlyForecastModel
import java.util.*

class HourlyForecastAdapter(
        private val hourly: ArrayList<HourlyForecastModel>
) : RecyclerView.Adapter<HourlyForecastAdapter.HourlyForecastViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = HourlyForecastViewHolder(
            LayoutInflater.from(parent.context)
                    .inflate(R.layout.hourly_list_item, parent, false)
    )

    override fun onBindViewHolder(holder: HourlyForecastViewHolder, position: Int) {
        holder.iconImage.setImageResource(hourly[position].icon)
        holder.summaryText.text = hourly[position].summary
        holder.temperatureText.text = hourly[position].temperature.toString()
        holder.timeText.text = hourly[position].time
    }

    override fun getItemCount() = hourly.size

    inner class HourlyForecastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val timeText: TextView = itemView.findViewById(R.id.timeText)
        val temperatureText: TextView = itemView.findViewById(R.id.temperatureText)
        val summaryText: TextView = itemView.findViewById(R.id.summaryText)
        val iconImage: ImageView = itemView.findViewById(R.id.iconImage)
    }
}