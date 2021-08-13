package android.example.com.weather.day

import android.example.com.weather.adapters.ForecastDayRecyclerAdapter
import android.example.com.weather.databinding.FragmentForecastDayBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager


class ForecastDayFragment : Fragment() {

    private lateinit var binding: FragmentForecastDayBinding
    private lateinit var viewModel: ForecastDayViewModel
    private var cityName: String? =""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_forecast_day, container, false)
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
            Toast.makeText(activity,cityName, Toast.LENGTH_LONG).show()
        }
    }
    fun setViewModel(){
        val application = requireNotNull(this.activity).application
        val viewModelFactory = ForecastDayViewModelFactory(application, cityName,this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ForecastDayViewModel::class.java)
        binding.viewModel = viewModel
    }
    fun observeForecastDay(){
        viewModel.forecastDay.observe(viewLifecycleOwner, Observer { forecastDay->
            binding.recyclerDay.layoutManager = LinearLayoutManager(activity)
            val adapter = ForecastDayRecyclerAdapter(forecastDay.list)
            binding.recyclerDay.adapter = adapter
        })
    }


}