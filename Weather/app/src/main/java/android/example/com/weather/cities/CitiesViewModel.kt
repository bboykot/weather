package android.example.com.weather.cities

import android.app.Application
import android.example.com.weather.data.ForecastCurrent
import android.example.com.weather.db.CitiesDao
import android.example.com.weather.db.CitiesEntity
import android.example.com.weather.network.WeatherApi
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.util.*

class CitiesViewModel(application: Application,val datasource: CitiesDao) : AndroidViewModel(application) {

    val applicationn = application
    val cities: MutableLiveData<List<CitiesEntity>> = MutableLiveData<List<CitiesEntity>>()
    val defaultCity = MutableLiveData<CitiesEntity>()
    var forecastCurrent = MutableLiveData<MutableList<ForecastCurrent>>()
    //lateinit var citiesForecast: MutableList<ForecastCurrent>

    init {
        forecastCurrent.value = ArrayList()
        selectData()
        selectDefaultCity()
        //loadCitiesForecast()
    }

    fun selectData(){
        viewModelScope.launch {
            try {
                cities.value = datasource.selectCities()
                loadCitiesForecast()
            }
            catch (e: Exception){Toast.makeText(applicationn.baseContext,"Нет сохраненных городов",Toast.LENGTH_LONG).show()}
        }
    }
    //временная функция для теста, она будет использоваться потом во фрагменте домашнем
    fun selectDefaultCity(){
        viewModelScope.launch {
            defaultCity.value = datasource.selectDefaultCity(true)
        }
    }

    //Загружаем текущий прогноз для всех сохраненных городов
    suspend fun loadCitiesForecast(){
        if (cities.value != null) {
            //citiesForecast = mutableListOf()
            for (city in cities.value!!) {
                //forecastCurrent.value = WeatherApi.retrofitService.getCurrentForecast(city.name)
                viewModelScope.launch {
                    //citiesForecast.add(WeatherApi.retrofitService.getCurrentForecast(city.name!!))
                    forecastCurrent.value?.add(WeatherApi.retrofitService.getCurrentForecast(city.name!!))
                    forecastCurrent.value = forecastCurrent.value

                }
            }
            //forecastCurrent.value = citiesForecast
        }
        else Toast.makeText(applicationn.baseContext, "noCities Data",Toast.LENGTH_LONG).show()
    }
    fun setNewDefaultCity(id: Long){
        viewModelScope.launch {
            datasource.changeDefaultFlag(false,defaultCity.value?.id)
            datasource.changeDefaultFlag(true,id)
        }
    }
}