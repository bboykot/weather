package android.example.com.weather.home

import android.app.Application
import android.example.com.weather.db.CitiesDao
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class HomeViewModelFactory (private val application: Application, private val datasource: CitiesDao) : ViewModelProvider.Factory{
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(application,datasource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}