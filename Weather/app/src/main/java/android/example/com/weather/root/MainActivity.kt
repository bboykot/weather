package android.example.com.weather.root

import android.example.com.weather.R
import android.example.com.weather.cities.CitiesFragment
import android.example.com.weather.databinding.ActivityMainBinding
import android.example.com.weather.home.HomeFragment
import android.example.com.weather.search.SearchFragment
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil

class MainActivity : AppCompatActivity(), AppNavigation {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        setBinding()
        setBottomNavigation()
        setStartDestination(savedInstanceState)

    }
    fun setBinding(){
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }
    fun setBottomNavigation(){
        binding.bottomNav.setOnItemSelectedListener { item->
            when(item.itemId){
                R.id.bottom_menu_search -> SearchFragment()
                R.id.bottom_menu_cities -> CitiesFragment()
                R.id.bottom_menu_my_city -> HomeFragment()
                else -> null

            }?.also { moveTo(supportFragmentManager, R.id.fragment_container,it) }

            true
        }
    }
    fun setStartDestination(savedInstanceState: Bundle?){
        if(savedInstanceState == null){
            findViewById<View>(R.id.bottom_menu_my_city).callOnClick()
        }
    }
}