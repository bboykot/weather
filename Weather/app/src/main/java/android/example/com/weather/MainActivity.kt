package android.example.com.weather

import android.example.com.weather.data.ForecastDay
import android.example.com.weather.data.ForecastWeek
import android.example.com.weather.network.WeatherApi
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var forecastDay: ForecastDay
    private lateinit var forecastWeek: ForecastWeek
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tv = findViewById<TextView>(R.id.tv_main)
        val tvWeek = findViewById<TextView>(R.id.tv_week)
        GlobalScope.launch {
            forecastWeek = WeatherApi.retrofitService.getWeekForecast("London")
            forecastDay = WeatherApi.retrofitService.getDayForecast("Rostov-on-Don")

        }

        tv.setOnClickListener {
            tv.text = forecastDay.toString()
        }
        tvWeek.setOnClickListener {
            tvWeek.text = forecastWeek.toString()
        }
    }
}