package android.example.com.weather

import android.example.com.weather.cities.CitiesFragment
import android.example.com.weather.data.ForecastDay
import android.example.com.weather.data.ForecastWeek
import android.example.com.weather.databinding.ActivityMainBinding
import android.example.com.weather.home.HomeFragment
import android.example.com.weather.root.AppNavigation
import android.example.com.weather.search.SearchFragment
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil

class MainActivity : AppCompatActivity(), AppNavigation {
    private lateinit var binding: ActivityMainBinding
    private lateinit var forecastDay: ForecastDay
    private lateinit var forecastWeek: ForecastWeek
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        setBinding()
        setBottomNavigation()

//        val tv = findViewById<TextView>(R.id.tv_main)
//        val tvWeek = findViewById<TextView>(R.id.tv_week)
//        GlobalScope.launch {
//            forecastWeek = WeatherApi.retrofitService.getWeekForecast("London")
//            forecastDay = WeatherApi.retrofitService.getDayForecast("Rostov-on-Don")
//
//        }
//
//        tv.setOnClickListener {
//            tv.text = forecastDay.toString()
//        }
//        tvWeek.setOnClickListener {
//            tvWeek.text = forecastWeek.toString()
//        }
    }
    fun setBinding(){
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
    }
    fun setBottomNavigation(){
        binding.bottomNav.setOnItemSelectedListener { item->
            when(item.itemId){
                R.id.bottom_menu_search -> SearchFragment()
                R.id.bottom_menu_cities -> CitiesFragment()
                R.id.bottom_menu_my_city -> HomeFragment()
                else -> null

            }?.also { moveTo(supportFragmentManager,R.id.fragment_container,it) }

            true
        }
    }
}