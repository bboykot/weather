package android.example.com.weather.search

import android.app.Application
import android.example.com.weather.data.ForecastCurrent
import android.example.com.weather.network.WeatherApi
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class SearchViewModel(application: Application) : AndroidViewModel(application){

    val applicationn = application
    var forecastCurrent = MutableLiveData<ForecastCurrent>()

    fun loadForecastDay(city: String){
        viewModelScope.launch {
            try {
                forecastCurrent.value = WeatherApi.retrofitService.getCurrentForecast(city)
            }
            catch (e: Exception){
                Toast.makeText(applicationn.baseContext, "Wrong name or no internet connection",Toast.LENGTH_LONG).show()}
        }
    }

}