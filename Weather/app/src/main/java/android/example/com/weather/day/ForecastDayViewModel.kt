package android.example.com.weather.day

import android.app.Application
import android.example.com.weather.data.ForecastDay
import android.example.com.weather.network.WeatherApi
import android.widget.Toast
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ForecastDayViewModel(application: Application,private val fragment: ForecastDayFragment,) : AndroidViewModel(application) {
    private val applicationn = application

    private val forecastDay = MutableLiveData<ForecastDay>()
    val imForecastDay: LiveData<ForecastDay>
    get() = forecastDay

    private var cityName : String? ="no data"


    init {
        getCityNameAndLoadForecast()
    }

    fun getCityNameAndLoadForecast(){

        fragment.setFragmentResultListener("DayForecast"){requestKey, bundle ->
            cityName = bundle.getString("name")
           loadForecastDay(cityName)

        }

    }
    fun loadForecastDay(cityName: String?){
        viewModelScope.launch {
            try {
                forecastDay.value = WeatherApi.retrofitService.getDayForecast(cityName!!)
            }
            catch (e: Exception){Toast.makeText(applicationn,"No internet connection",Toast.LENGTH_LONG).show()}
        }
    }

}