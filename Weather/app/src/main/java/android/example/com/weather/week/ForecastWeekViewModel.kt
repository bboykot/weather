package android.example.com.weather.week

import android.app.Application
import android.example.com.weather.data.ForecastWeek
import android.example.com.weather.network.WeatherApi
import android.widget.Toast
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ForecastWeekViewModel(application: Application,private val fragment: ForecastWeekFragment) : AndroidViewModel(application) {

    private val applicationn = application

    private val forecastWeek = MutableLiveData<ForecastWeek>()
    val imForecastWeek: LiveData<ForecastWeek>
    get() = forecastWeek

    private var cityName : String? =""

    init {
        getCityNameAndLoadForecast()
    }

    fun getCityNameAndLoadForecast(){
        fragment.setFragmentResultListener("WeekForecast"){requestKey, bundle ->
            cityName = bundle.getString("name")
            loadForecastWeek()
        }
    }

    fun loadForecastWeek(){
        viewModelScope.launch {
            try {
                forecastWeek.value = WeatherApi.retrofitService.getWeekForecast(cityName!!)
            }
            catch (e: Exception){
                Toast.makeText(applicationn,"No internet connection", Toast.LENGTH_LONG).show()}
        }
    }
}