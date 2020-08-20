package com.example.nocuscountries.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nocuscountries.R
import com.example.nocuscountries.adapter.CountryListAdapter
import com.example.nocuscountries.viewModel.CountryByNameViewModel
import kotlinx.android.synthetic.main.activity_country_by_name.*

class CountryByNameActivity: AppCompatActivity() {

    private lateinit var viewModel: CountryByNameViewModel
    private lateinit var adapter : CountryListAdapter

    init{
        setupViewModel()
        setupAdapter()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this).get(CountryByNameViewModel::class.java)
    }

    private fun setupAdapter() {
        adapter = CountryListAdapter(baseContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_by_name)

        setupSearchView()
        setupRecyclerView()
        observeCountries()
    }

    private fun setupSearchView() {
        TODO("Not yet implemented")
    }

    private fun observeCountries() {
        viewModel.countries.observe(this, Observer{ allCountries ->
            adapter.setData(allCountries)
        })
    }

    private fun setupRecyclerView() {
        countryByNameRecyclerView.layoutManager = LinearLayoutManager(baseContext)
        countryByNameRecyclerView.adapter = adapter
    }
}