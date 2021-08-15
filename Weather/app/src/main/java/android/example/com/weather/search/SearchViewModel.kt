package android.example.com.weather.search

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

class SearchViewModel(application: Application,private val datasource: CitiesDao) : AndroidViewModel(application){

    private val applicationn = application

    private var forecastCurrent = MutableLiveData<ForecastCurrent>()
    val imForecastCurrent: LiveData<ForecastCurrent>
    get() = forecastCurrent

    private var defaultCity = MutableLiveData<CitiesEntity>()

    init {
        selectDefaultCity()
    }

    fun loadForecastDay(city: String){
        viewModelScope.launch {
            try {
                forecastCurrent.value = WeatherApi.retrofitService.getCurrentForecast(city)
            }
            catch (e: Exception){
                Toast.makeText(applicationn.baseContext, "Неправильное имя или нет подключения к сети",Toast.LENGTH_LONG).show()}
        }
    }
    fun saveCity(){
        viewModelScope.launch {
            if (forecastCurrent.value?.id != null){
                datasource.insertCity(CitiesEntity(id = forecastCurrent.value?.id,name = forecastCurrent.value?.name,false))
            }
            else{
                Toast.makeText(applicationn.baseContext, "Прежде выполните поиск города",Toast.LENGTH_SHORT).show()
            }
        }
    }
    //стираем флаг умолчания старого города установленного по умолчанию и ставим флаг новому городу
    fun saveCityAsDefault(){
            viewModelScope.launch {
                if (forecastCurrent.value?.id != null){
                    datasource.changeDefaultFlag(false,defaultCity.value?.id)
                    datasource.insertCity(CitiesEntity(id = forecastCurrent.value?.id, name = forecastCurrent.value?.name, defaultCity = true))
                }
                else{
                    Toast.makeText(applicationn.baseContext, "Прежде выполните поиск города",Toast.LENGTH_SHORT).show()
                }
            }
    }
    //Узнаем город по умолчанию, чтобы в дальнейшем иметь возможность изменить флаг города по умолчанию
    fun selectDefaultCity(){
        viewModelScope.launch {
            defaultCity.value = datasource.selectDefaultCity(true)
        }
    }
}