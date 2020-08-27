package com.example.nocuscountries.countriesWithSearch

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nocuscountries.R
import com.example.nocuscountries.countries.CountriesAdapter
import com.example.nocuscountries.countries.CountryInfo
import kotlinx.android.synthetic.main.fragment_countries.*

class CountriesFragment(
    private var viewModel: CountriesWithSearchViewModel
) : Fragment() {

    private lateinit var adapter: CountriesAdapter
    private lateinit var countries: ArrayList<CountryInfo>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_countries, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()
        observeCountries()
        setupRecyclerView()
    }

    private fun setupAdapter() {
        adapter = CountriesAdapter(context as Context)
    }

    fun search(query: String): Boolean {

        adapter.clearData()

        if(query.isEmpty()){
            adapter.setData(countries)
            return false
        }

        viewModel.search(query)?.observe(this, Observer {

            // check this out
            // we have already assigned the value to the recyclerview
            // we did so on create
            // this part will only be triggered so where along the line when an event occurs
            // but we are going to modify this variable that is not supposed to be linked to the
            // the recyclerview in hopes that the recyclerview will change
            adapter.addNewData(it)

        })

        return false
    }

    private fun observeCountries() {
        viewModel.countries?.observe(viewLifecycleOwner, Observer { countries ->
            this.countries = countries
            adapter.setData( this.countries)
        })
    }

    private fun setupRecyclerView() {
        countriesRecyclerView.layoutManager = LinearLayoutManager(context)
        countriesRecyclerView.adapter = adapter
    }
}