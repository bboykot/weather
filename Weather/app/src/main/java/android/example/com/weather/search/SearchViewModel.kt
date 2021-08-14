package android.example.com.weather.search

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

class SearchViewModel(application: Application, val datasource: CitiesDao) : AndroidViewModel(application){

    val applicationn = application
    var forecastCurrent = MutableLiveData<ForecastCurrent>()
    var defaultCity = MutableLiveData<CitiesEntity>()

    init {
        selectDefaultCity()
    }

    fun loadForecastDay(city: String){
        viewModelScope.launch {
            try {
                forecastCurrent.value = WeatherApi.retrofitService.getCurrentForecast(city)
            }
            catch (e: Exception){
                Toast.makeText(applicationn.baseContext, "Wrong name or no internet connection",Toast.LENGTH_LONG).show()}
        }
    }
    fun saveCity(){
        viewModelScope.launch {
            datasource.insertCity(CitiesEntity(id = forecastCurrent.value?.id,name = forecastCurrent.value?.name,false))
        }
    }
    //стираем флаг умолчания старого города установленного по умолчанию и ставим флаг новому городу
    fun saveCityAsDefault(){
            viewModelScope.launch {
                datasource.changeDefaultFlag(false,defaultCity.value?.id)
                datasource.insertCity(CitiesEntity(id = forecastCurrent.value?.id, name = forecastCurrent.value?.name, defaultCity = true))
            }
    }
    //Узнаем город по умолчанию, чтобы в дальнейшем иметь возможность изменить флаг города по умолчанию
    fun selectDefaultCity(){
        viewModelScope.launch {
            defaultCity.value = datasource.selectDefaultCity(true)
        }
    }
}