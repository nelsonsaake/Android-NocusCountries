package com.example.nocuscountries.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.example.nocuscountries.R
import com.example.nocuscountries.factory.MainViewModelFactory
import com.example.nocuscountries.ui.main.SectionsPagerAdapter
import com.example.nocuscountries.viewModel.MainViewModel
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class CountryInfoActivity : AppCompatActivity() {

    private val ALPHA_2_CODE: String = "alpha2code"

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_info)
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)

        // we expect the alpha2code of the country in the savedInstanceState bundle
        val alpha2code = savedInstanceState?.getString(ALPHA_2_CODE)

        // we will take that and use it find the country we want
        val viewModelFactory = MainViewModelFactory(baseContext, this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        viewModel.countries.observe(this, Observer { allCountries ->
            val countryInfo = allCountries.filter{ info -> info.alpha2Code == alpha2code }.single()

            // when we find the country,
            val jsonString = Gson().toJson(countryInfo)
            val type: Type = object :
                TypeToken<Map<String?, String>?>() {}.type
            val map: Map<String, String> = Gson().fromJson(jsonString, type)
        })

        // we will convert it to json string
        // convert that json string back to a map
        // we will access the values of the map by index and use that to display the country info
        // on the adapter view


    }


}