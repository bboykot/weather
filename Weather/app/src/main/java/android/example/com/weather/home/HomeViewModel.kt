package android.example.com.weather.home

import android.app.Application
import android.example.com.weather.data.ForecastDay
import android.example.com.weather.db.CitiesDao
import android.example.com.weather.db.CitiesEntity
import android.example.com.weather.network.WeatherApi
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class HomeViewModel(application: Application,private val datasource: CitiesDao) : AndroidViewModel(application) {
    private val applicationn = application

    private var defaultCity = MutableLiveData<CitiesEntity>()
    val imDefaultCity: LiveData<CitiesEntity>
    get() = defaultCity

    private var forecastDay = MutableLiveData<ForecastDay>()
    val imForecastDay: LiveData<ForecastDay>
    get() = forecastDay

    private var showNotifyNoCity: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    val imShowNotifyNoCity: LiveData<Boolean>
    get() = showNotifyNoCity

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
            showNotifyNoCity.value = false
            defaultCity.value = datasource.selectDefaultCity(true)
            if (defaultCity.value != null){ loadDataFromIntenet() }
            else {
                Toast.makeText(applicationn.baseContext, "Не задан город по умолчанию",Toast.LENGTH_SHORT).show()
                showNotifyNoCity.value = true
                }
            }
        }
}