package android.example.com.weather.day

import android.example.com.weather.adapters.ForecastDayRecyclerAdapter
import android.example.com.weather.databinding.FragmentForecastDayBinding
import android.example.com.weather.db.CitiesDao
import android.example.com.weather.db.CitiesDataBase
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager


class ForecastDayFragment : Fragment() {

    private lateinit var binding: FragmentForecastDayBinding
    private lateinit var viewModel: ForecastDayViewModel
    private var cityName: String? =""
    private lateinit var dataSource: CitiesDao

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        getCityName()
        setBinding(inflater)
        setViewModel()
        observeForecastDay()

        return binding.root
    }

    fun setBinding(inflater: LayoutInflater){
        binding = FragmentForecastDayBinding.inflate(inflater)
        binding.lifecycleOwner = this
    }
    fun getCityName(){
        setFragmentResultListener("DayForecast"){requestKey, bundle ->
            cityName = bundle.getString("name")
        }
    }
    fun setViewModel(){
        val application = requireNotNull(this.activity).application
        dataSource = CitiesDataBase.getInstance(application).citiesDao
        val viewModelFactory = ForecastDayViewModelFactory(application, this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ForecastDayViewModel::class.java)
        binding.viewModel = viewModel
    }
    fun observeForecastDay(){
        viewModel.imForecastDay.observe(viewLifecycleOwner, Observer { forecastDay->
            binding.recyclerDay.layoutManager = LinearLayoutManager(activity)
            val adapter = ForecastDayRecyclerAdapter(forecastDay.list)
            binding.recyclerDay.adapter = adapter
        })
    }


}