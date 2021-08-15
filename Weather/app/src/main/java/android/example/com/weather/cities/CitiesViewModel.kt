package android.example.com.weather.cities

import android.app.Application
import android.example.com.weather.data.ForecastCurrent
import android.example.com.weather.db.CitiesDao
import android.example.com.weather.db.CitiesEntity
import android.example.com.weather.network.WeatherApi
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.util.*

class CitiesViewModel(application: Application,private val datasource: CitiesDao) : AndroidViewModel(application) {

    private val applicationn = application
    private var cities: MutableLiveData<List<CitiesEntity>> = MutableLiveData<List<CitiesEntity>>()
    private val defaultCity = MutableLiveData<CitiesEntity>()

    private var forecastCurrent = MutableLiveData<MutableList<ForecastCurrent>>()
    val imForecastCurrent: LiveData<MutableList<ForecastCurrent>>
    get() = forecastCurrent

    init {
        forecastCurrent.value = ArrayList()
        selectDefaultCity()
        loadCitiesForecast1()

    }

    fun selectDefaultCity(){
        viewModelScope.launch {
            defaultCity.value = datasource.selectDefaultCity(true)
        }
    }

    fun setNewDefaultCity(id: Long){
        viewModelScope.launch {
            datasource.changeDefaultFlag(false,defaultCity.value?.id)
            datasource.changeDefaultFlag(true,id)
        }
    }

     fun loadCitiesForecast1(){

        viewModelScope.launch {
            cities.value = datasource.selectCities()
            if (cities.value?.size == 0){Toast.makeText(applicationn.baseContext, "Нет сохраненных городов",Toast.LENGTH_SHORT).show()}
            else load()
        }
    }

    suspend fun load(){
        for (city in cities.value!!) {
            try {
                forecastCurrent.value?.add(WeatherApi.retrofitService.getCurrentForecast(city.name!!))
                forecastCurrent.value = forecastCurrent.value
            }
            catch (e:Exception){Toast.makeText(applicationn.baseContext, "Нет подключения к сети",Toast.LENGTH_LONG).show()}
        }
    }
}