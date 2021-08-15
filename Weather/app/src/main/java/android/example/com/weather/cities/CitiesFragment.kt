package android.example.com.weather.cities

import android.example.com.weather.adapters.CitiesRecyclerAdapter
import android.example.com.weather.databinding.FragmentCitiesBinding
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

class CitiesFragment : Fragment() {

    private lateinit var binding: FragmentCitiesBinding
    private lateinit var datasource: CitiesDao
    private lateinit var viewModel: CitiesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setBinding(inflater)
        setViewModel()
        observeForecastCurrent()

        return binding.root
    }

    fun setBinding(inflater: LayoutInflater){
        binding = FragmentCitiesBinding.inflate(inflater)
        binding.lifecycleOwner = this
    }
    fun setViewModel(){
        val application = requireNotNull(this.activity).application
        datasource = CitiesDataBase.getInstance(application).citiesDao
        val viewModelFactory = CitiesViewModelFactory(application,datasource)
        viewModel = ViewModelProvider(this,viewModelFactory).get(CitiesViewModel::class.java)
        binding.viewModel = viewModel
    }
    fun observeForecastCurrent(){
        viewModel.imForecastCurrent.observe(viewLifecycleOwner, Observer { forecastCurrent ->
            binding.citiesRecycler.layoutManager = LinearLayoutManager(activity)
            val adapter = CitiesRecyclerAdapter(forecastCurrent, viewModel, this)
            binding.citiesRecycler.adapter = adapter
        })
    }
}