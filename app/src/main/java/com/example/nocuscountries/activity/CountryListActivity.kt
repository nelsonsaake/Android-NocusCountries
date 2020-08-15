package com.example.nocuscountries.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.nocuscountries.R
import com.example.nocuscountries.fragment.CountryListFragment

class CountryListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_list)

        viewAllCountries()
    }

    private fun viewAllCountries() =
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, CountryListFragment.newInstance())
            .commitNow()

    private fun countryPage() =
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, CountryListFragment.newInstance())
            .commitNow()
}