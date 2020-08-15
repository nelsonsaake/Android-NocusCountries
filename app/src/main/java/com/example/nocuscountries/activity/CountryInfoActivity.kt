package com.example.nocuscountries.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nocuscountries.R
import com.example.nocuscountries.adapter.CountryInfoAdapter
import com.example.nocuscountries.factory.MainViewModelFactory
import com.example.nocuscountries.viewModel.MainViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.content_country_info.*
import java.lang.reflect.Type

class CountryInfoActivity : AppCompatActivity() {

    private val ALPHA_2_CODE: String = "alpha2code"

    private var viewModel: MainViewModel
    private val adapter : CountryInfoAdapter = CountryInfoAdapter(baseContext)
    private var savedInstanceState: Bundle? = null

    init{
        val viewModelFactory = MainViewModelFactory(baseContext, this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_info)

        this.savedInstanceState = savedInstanceState
        setupRecyclerView()
        observeCountry()
    }

    private fun setupRecyclerView() {
        countryInfoRecyclerView.layoutManager = LinearLayoutManager(baseContext)
        countryInfoRecyclerView.adapter = adapter
    }

    private fun observeCountry(){
        // we will take that and use it find the country we want
        viewModel.countries.observe(this, Observer { allCountries ->
            val countryInfo = allCountries.single { info -> info.alpha2Code == getAlpha2code() }
            val jsonString = Gson().toJson(countryInfo)
            val type: Type = object : TypeToken<Map<String?, String>?>() {}.type
            val map: Map<String, String> = Gson().fromJson(jsonString, type)
            adapter.setData(map)
        })
    }

    private fun getAlpha2code(): String = savedInstanceState?.getString(ALPHA_2_CODE) ?: ""
}