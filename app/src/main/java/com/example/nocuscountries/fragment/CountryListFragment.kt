package com.example.nocuscountries.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nocuscountries.R
import com.example.nocuscountries.adapter.CountryListAdapter
import com.example.nocuscountries.factory.MainViewModelFactory
import com.example.nocuscountries.viewModel.MainViewModel
import kotlinx.android.synthetic.main.fragment_country_list.*

class CountryListFragment : androidx.fragment.app.Fragment() {

    companion object {
        fun newInstance() =
            CountryListFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_country_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getCountriesFromApi()
    }

    private fun getCountriesFromApi() {

        val viewModelFactory = MainViewModelFactory(this.context as Context, this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        // setup live data something for when we get data back from the api
        viewModel.countries.observe(viewLifecycleOwner, Observer {
            countryRecyclerView.layoutManager = LinearLayoutManager(context)
            countryRecyclerView.adapter = CountryListAdapter(this!!.requireContext(),
                viewModel.countries.value!!)
        })
    }
}



