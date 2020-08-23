package com.example.nocuscountries.countries

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nocuscountries.R
import kotlinx.android.synthetic.main.fragment_countries.*

class CountriesFragment : androidx.fragment.app.Fragment() {

    companion object {
        fun newInstance() =
            CountriesFragment()
    }

    private lateinit var viewModel: CountriesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_countries, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getCountriesFromApi()
    }

    private fun getCountriesFromApi() {

        val viewModelFactory =
            CountriesModelFactory(
                this.context as Context,
                this
            )
        viewModel = ViewModelProvider(this, viewModelFactory).get(CountriesViewModel::class.java)

        // setup live data something for when we get data back from the api
        viewModel.countries.observe(viewLifecycleOwner, Observer {
            countriesRecyclerView.layoutManager = LinearLayoutManager(context)
            countriesRecyclerView.adapter =
                CountriesAdapter(
                    this!!.requireContext(),
                    viewModel.countries.value!!
                )
        })
    }
}



