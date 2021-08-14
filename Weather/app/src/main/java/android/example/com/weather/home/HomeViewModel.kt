package android.example.com.weather.home

import android.app.Application
import android.example.com.weather.data.ForecastDay
import android.example.com.weather.db.CitiesDao
import android.example.com.weather.db.CitiesEntity
import android.example.com.weather.network.WeatherApi
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class HomeViewModel(application: Application, val datasource: CitiesDao) : AndroidViewModel(application) {
    val applicationn = application
    var defaultCity = MutableLiveData<CitiesEntity>()
    var forecastDay = MutableLiveData<ForecastDay>()
    var defaultCitySetted: Boolean = true

    init {
        loadForecastDay()
    }


    fun loadForecastDay(){
        viewModelScope.launch {
            try {
                defaultCity.value = datasource.selectDefaultCity(true)
                forecastDay.value = WeatherApi.retrofitService.getDayForecast(defaultCity.value?.name!!)
                defaultCitySetted = false
            }
            catch (e: Exception){
                Toast.makeText(applicationn,"Не задан город по умолчанию", Toast.LENGTH_LONG).show()}
        }
    }
}