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

        setBinding(inflater)
        setViewModel()
        setSearchClickListener()

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
}