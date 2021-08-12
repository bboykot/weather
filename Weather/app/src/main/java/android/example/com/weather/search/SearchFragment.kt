package android.example.com.weather.search

import android.example.com.weather.databinding.FragmentSearchBinding
import android.example.com.weather.db.CitiesDao
import android.example.com.weather.db.CitiesDataBase
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider


class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var viewModel: SearchViewModel
    private lateinit var dataSource: CitiesDao
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_search, container, false)
        setBinding(inflater)
        setViewModel()
        setSearchClickListener()
        setSaveClickListener()
        setSaveCityAsDefaultClickListener()
        //observeDataFromInternet()

        return binding.root
    }

    fun setBinding(inflater: LayoutInflater){
        binding = FragmentSearchBinding.inflate(inflater)
        binding.lifecycleOwner = this
    }
    fun setViewModel(){
        val application= requireNotNull(this.activity).application
        dataSource = CitiesDataBase.getInstance(application).citiesDao
        val viewModelFactory = SearchViewModelFactory(application,dataSource)
        viewModel = ViewModelProvider(this,viewModelFactory).get(SearchViewModel::class.java)
        binding.viewModel = viewModel
    }

    fun setSearchClickListener(){
        binding.btnSearch.setOnClickListener {
            val city = binding.etSearch.text.toString()
            viewModel.loadForecastDay(city)
        }
    }
    fun setSaveClickListener(){
        binding.brnSaveCity.setOnClickListener {
            viewModel.saveCity()
//            GlobalScope.launch {
//                dataSource.insertCity(
//                    CitiesEntity(
//                        id = viewModel.forecastCurrent.value?.id,
//                        name = viewModel.forecastCurrent.value?.name
//                    )
//                )
//            }
        }
    }
    fun setSaveCityAsDefaultClickListener(){
        binding.btnSaveSetDefault.setOnClickListener {
            viewModel.saveCityAsDefault()
        }
    }
//    fun observeDataFromInternet(){
//        viewModel.forecastDay.observe(viewLifecycleOwner, Observer { forecastDay->
//            //binding.searchTest.text = viewModel.forecastDay.value.toString()
//        })
//    }
}