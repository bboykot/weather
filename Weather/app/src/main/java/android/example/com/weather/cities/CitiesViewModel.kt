package android.example.com.weather.cities

import android.app.Application
import android.example.com.weather.db.CitiesDao
import android.example.com.weather.db.CitiesEntity
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class CitiesViewModel(application: Application,val datasource: CitiesDao) : AndroidViewModel(application) {

    val applicationn = application
    val cities = MutableLiveData<List<CitiesEntity>>()

    init {
        selectData()
    }

    fun selectData(){
        viewModelScope.launch {
            try {
            cities.value = datasource.selectCities()
            }
            catch (e: Exception){Toast.makeText(applicationn.baseContext,"No data",Toast.LENGTH_LONG).show()}
        }
    }
}