package android.example.com.weather.week

import android.app.Application
import android.example.com.weather.data.ForecastWeek
import android.example.com.weather.network.WeatherApi
import android.widget.Toast
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ForecastWeekViewModel(application: Application, val fragment: ForecastWeekFragment) : AndroidViewModel(application) {

    val applicationn = application
    val forecastWeek = MutableLiveData<ForecastWeek>()
    var cityName : String? =""

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