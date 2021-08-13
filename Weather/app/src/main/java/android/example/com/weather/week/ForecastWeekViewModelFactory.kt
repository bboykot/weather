package android.example.com.weather.week

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ForecastWeekViewModelFactory(private val application: Application, private val fragment: ForecastWeekFragment) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ForecastWeekViewModel::class.java)) {
            return ForecastWeekViewModel(application,fragment) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}