package com.example.nocuscountries.countries

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.nocuscountries.R

class CountriesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_list)

        viewAllCountries()
    }

    private fun viewAllCountries() =
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, CountriesFragment.newInstance())
            .commitNow()

    private fun countryPage() =
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, CountriesFragment.newInstance())
            .commitNow()
}