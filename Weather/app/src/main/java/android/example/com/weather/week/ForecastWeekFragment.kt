package android.example.com.weather.week

import android.example.com.weather.adapters.ForecastWeekRecyclerAdapter
import android.example.com.weather.databinding.FragmentForecastWeekBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

class ForecastWeekFragment : Fragment() {


    private lateinit var binding: FragmentForecastWeekBinding
    private lateinit var viewModel: ForecastWeekViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setBinding(inflater)
        setViewModel()
        observeForecastWeek()

        return binding.root
    }
    fun setBinding(inflater: LayoutInflater){
        binding = FragmentForecastWeekBinding.inflate(inflater)
        binding.lifecycleOwner = this
    }
    fun setViewModel(){
        val application = requireNotNull(this.activity).application
        val viewModelFactory = ForecastWeekViewModelFactory(application, this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ForecastWeekViewModel::class.java)
        binding.viewModel = viewModel
    }
    fun observeForecastWeek(){
        viewModel.forecastWeek.observe(viewLifecycleOwner, Observer { forecastWeek ->
            binding.recyclerWeek.layoutManager = LinearLayoutManager(activity)
            val adapter = ForecastWeekRecyclerAdapter(forecastWeek.list)
            binding.recyclerWeek.adapter = adapter
        })
    }
}