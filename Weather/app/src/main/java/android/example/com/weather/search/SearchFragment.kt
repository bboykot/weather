package android.example.com.weather.search

import android.example.com.weather.databinding.FragmentSearchBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider


class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var viewModel: SearchViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_search, container, false)
        setBinding(inflater)
        setViewModel()
        setSearchClickListener()
        //observeDataFromInternet()

        return binding.root
    }

    fun setBinding(inflater: LayoutInflater){
        binding = FragmentSearchBinding.inflate(inflater)
        binding.lifecycleOwner = this
    }
    fun setViewModel(){
        val application= requireNotNull(this.activity).application
        val viewModelFactory = SearchViewModelFactory(application)
        viewModel = ViewModelProvider(this,viewModelFactory).get(SearchViewModel::class.java)
        binding.viewModel = viewModel
    }

    fun setSearchClickListener(){
        binding.btnSearch.setOnClickListener {
            val city = binding.etSearch.text.toString()
            viewModel.loadForecastDay(city)
        }
    }
//    fun observeDataFromInternet(){
//        viewModel.forecastDay.observe(viewLifecycleOwner, Observer { forecastDay->
//            //binding.searchTest.text = viewModel.forecastDay.value.toString()
//        })
//    }
}