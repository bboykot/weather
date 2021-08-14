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
    var defaultt: MutableLiveData<Boolean> = MutableLiveData<Boolean>()

    init {
        displayDefaultCityForecast()
    }

    suspend fun loadDataFromIntenet(){
        try {
            forecastDay.value = WeatherApi.retrofitService.getDayForecast(defaultCity.value?.name!!)
        }
        catch (e:Exception){Toast.makeText(applicationn.baseContext, "Нет подключения к сети",Toast.LENGTH_LONG).show()}
    }
    fun displayDefaultCityForecast(){
        viewModelScope.launch {
            defaultt.value = false
            defaultCity.value = datasource.selectDefaultCity(true)
            if (defaultCity.value != null){ loadDataFromIntenet() }
            else {
                Toast.makeText(applicationn.baseContext, "Не задан город по умолчанию",Toast.LENGTH_SHORT).show()
                defaultt.value = true
                }
            }
        }
}