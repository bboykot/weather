package android.example.com.weather.day

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ForecastDayViewModelFactory(private val application: Application, private val cityName: String?, private val fragment: ForecastDayFragment) : ViewModelProvider.Factory{

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ForecastDayViewModel::class.java)) {
            return ForecastDayViewModel(application, cityName,fragment) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}