package com.example.paritosh.stormy

import com.example.paritosh.stormy.model.CurrentWeather
import com.example.paritosh.stormy.model.HourlyForecastModel
import okhttp3.*
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.util.*

class WeatherDataProvider {

    private val apiKey: String = "cde93abb7a85053efe6dcd43de69e1fa"
    private val longitude = 42.3601
    private val latitude = -71.0589
    private val forecastURL = "https://api.darksky.net/forecast/$apiKey/$longitude,$latitude"

    fun getCurrentWeather(onWeatherApiResponse: OnWeatherApiResponse) {

        val client = OkHttpClient()

        val request = Request.Builder()
                .url(forecastURL)
                .build()

        val call = client.newCall(request)
        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                onWeatherApiResponse.onError(e)
            }

            override fun onResponse(call: Call, response: Response) {


                val jsonData = response.body()!!.string()
                try {
                    onWeatherApiResponse.onSuccess(parseCurrentWeather(jsonData))
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
        })
    }

    private fun parseCurrentWeather(jsonData: String): CurrentWeather {

        val jsonObject = JSONObject(jsonData)
        val currently = jsonObject.getJSONObject("currently")

        val hourly = ArrayList<HourlyForecastModel>()
        val hourlyObject = jsonObject.getJSONObject("hourly")
        val hourlyData = hourlyObject.getJSONArray("data")

        for (i in 0 until hourlyData.length()) {

            val data = hourlyData.getJSONObject(i)
            val hourlyForecastModel = HourlyForecastModel()

            hourlyForecastModel.summary = data.getString("summary")
            hourlyForecastModel.temperature = data.getDouble("temperature")
            hourlyForecastModel.icon = Utils.getIconId(data.getString("icon"))
            hourlyForecastModel.time = Utils.getReadableTime(
                    data.getLong("time"),
                    jsonObject.getString("timezone")
            )

            hourly.add(i, hourlyForecastModel)
        }


        return CurrentWeather(
                jsonObject.getString("timezone"), currently.getString("time"), currently.getLong("time"),
                currently.getDouble("temperature"), currently.getDouble("humidity"),
                currently.getDouble("precipProbability"), currently.getString("summary"),
                hourly
        )
    }

    interface OnWeatherApiResponse {
        fun onSuccess(currentWeather: CurrentWeather)

        fun onError(t: Throwable)
    }
}