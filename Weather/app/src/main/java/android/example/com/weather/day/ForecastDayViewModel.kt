package android.example.com.weather.day

import android.app.Application
import android.example.com.weather.data.ForecastDay
import android.example.com.weather.network.WeatherApi
import android.widget.Toast
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ForecastDayViewModel(application: Application, cityName: String?,val fragment: ForecastDayFragment) : AndroidViewModel(application) {
    val applicationn = application
    val forecastDay = MutableLiveData<ForecastDay>()
    var cityName : String? =""
    var citName = MutableLiveData<String>()

    init {

        getCityNameLoadForecast()
    }

    fun getCityNameLoadForecast(){
        fragment.setFragmentResultListener("DayForecast"){requestKey, bundle ->
            cityName = bundle.getString("name")
            Toast.makeText(applicationn,"name is $cityName",Toast.LENGTH_LONG).show()
            loadForecastDay()
        }
    }
    fun loadForecastDay(){
        viewModelScope.launch {
            forecastDay.value = WeatherApi.retrofitService.getDayForecast(cityName!!)
            try {
                forecastDay.value = WeatherApi.retrofitService.getDayForecast(cityName!!)
            }
            catch (e: Exception){Toast.makeText(applicationn,"No internet connection",Toast.LENGTH_LONG).show()}
        }
    }

}