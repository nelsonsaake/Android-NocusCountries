package com.example.nocuscountries.countrySearcher

import android.os.Bundle
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nocuscountries.R
import com.example.nocuscountries.countries.CountriesAdapter
import com.example.nocuscountries.countries.CountryInfo
import kotlinx.android.synthetic.main.activity_country_by_name.*

class CountryWithSearchActivity : AppCompatActivity() {

    private lateinit var viewModel: CountryWithSearchViewModel
    private lateinit var adapter: CountriesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_by_name)

        setupViewModel()
        setupAdapter()
        setupSearchView()
        observeCountries()
        setupRecyclerView()
    }


    private fun setupViewModel() {
        viewModel = ViewModelProvider(this).get(CountryWithSearchViewModel::class.java)
    }

    private fun setupAdapter() {
        adapter = CountriesAdapter(this)
    }

    private fun setupSearchView() {

        searchView.isIconifiedByDefault = false

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                adapter.setData(ArrayList<CountryInfo>()) // clear results list before beginning new search
                search(searchView.query.toString())

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun search(query: String) {

        viewModel.search(query)?.observe(this, Observer {

            // check this out
            // we have already assigned the value to the recyclerview
            // we did so on create
            // this part will only be triggered so where along the line when an event occurs
            // but we are going to modify this variable that is not supposed to be linked to the
            // the recyclerview in hopes that the recyclerview will change
            adapter.addNewData(it)

        })
    }

    private fun observeCountries() {
        viewModel.countries?.observe(this, Observer { allCountries ->
            adapter.setData(allCountries)
        })
    }

    private fun setupRecyclerView() {
        countryByNameRecyclerView.layoutManager = LinearLayoutManager(this)
        countryByNameRecyclerView.adapter = adapter
    }
}