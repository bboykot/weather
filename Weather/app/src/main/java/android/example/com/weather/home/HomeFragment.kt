package android.example.com.weather.home

import android.example.com.weather.adapters.ForecastDayRecyclerAdapter
import android.example.com.weather.databinding.FragmentHomeBinding
import android.example.com.weather.db.CitiesDao
import android.example.com.weather.db.CitiesDataBase
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var dataSource: CitiesDao

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setBinding(inflater)
        setViewModel()
        observeForecastDay()

        return binding.root
    }

    fun setBinding(inflater: LayoutInflater){
        binding = FragmentHomeBinding.inflate(inflater)
        binding.lifecycleOwner = this
    }
    fun setViewModel(){
        val application= requireNotNull(this.activity).application
        dataSource = CitiesDataBase.getInstance(application).citiesDao
        val viewModelFactory = HomeViewModelFactory(application,dataSource)
        viewModel = ViewModelProvider(this,viewModelFactory).get(HomeViewModel::class.java)
        binding.viewModel = viewModel
    }
    fun observeForecastDay(){
        viewModel.forecastDay.observe(viewLifecycleOwner, Observer { forecastDay->
            binding.homeRecycler.layoutManager = LinearLayoutManager(activity)
            val adapter = ForecastDayRecyclerAdapter(forecastDay.list)
            binding.homeRecycler.adapter = adapter
        })
    }
}