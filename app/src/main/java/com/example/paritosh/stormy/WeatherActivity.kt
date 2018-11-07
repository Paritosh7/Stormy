package com.example.paritosh.stormy

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.method.LinkMovementMethod
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.paritosh.stormy.databinding.ActivityMainBinding
import com.example.paritosh.stormy.model.CurrentWeatherDataBindingModel
import com.example.paritosh.stormy.model.HourlyForecastModel
import java.util.*

class WeatherActivity : AppCompatActivity(), WeatherContract.WeatherView {

    private lateinit var hourlyData: ArrayList<HourlyForecastModel>
    private lateinit var binding: ActivityMainBinding

    private val presenter: WeatherContract.WeatherPresenter = WeatherPresenterImpl(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val textView = findViewById<TextView>(R.id.darkSkyAttribution)
        textView.movementMethod = LinkMovementMethod.getInstance()
        val refreshImageView = findViewById<ImageView>(R.id.refreshImageView)

        refreshImageView.setOnClickListener { presenter.refreshWeatherDetails() }

        presenter.updateWeatherDetails()
        val hourlyButton = findViewById<Button>(R.id.hourly_button)
        hourlyButton.setOnClickListener {
            val intent = Intent(this, HourlyForecastActivity::class.java)
            intent.putParcelableArrayListExtra("hourlyData", hourlyData)
            startActivity(intent)
        }

    }

    override fun render(model: CurrentWeatherDataBindingModel) {
        binding.weather = model
        hourlyData = model.hourly
    }

    override fun showMessage(messageResId: Int) {

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()
    }

    override fun showErrorMessage(titleResId: Int, messageResId: Int) {
        SabkaAlertDialogFragment
                .newInstance(titleResId, messageResId)
                .show(supportFragmentManager, "Error dialog")
    }

    override fun loadContext(): Context = this

}
