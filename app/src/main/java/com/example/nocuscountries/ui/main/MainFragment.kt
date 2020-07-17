package com.example.nocuscountries.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.example.nocuscountries.*
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : androidx.fragment.app.Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {

        super.onActivityCreated(savedInstanceState)

        val countryApiService = CountryApiService.create()
        val countryCache = CountryCache()
        val viewModelFactory = ViewModelFactory(CountryRepo(countryApiService, countryCache))
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)

        // setup live data something for when we get data back from the api
        viewModel.countries.observe(viewLifecycleOwner){
            message.text = viewModel.countries.toString()
        }
    }
}



