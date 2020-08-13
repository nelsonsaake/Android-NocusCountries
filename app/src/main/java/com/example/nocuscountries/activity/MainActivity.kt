package com.example.nocuscountries.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.nocuscountries.R
import com.example.nocuscountries.fragment.AllCountriesFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        if (savedInstanceState == null) {
            viewAllCountries()
        }
    }

    private fun viewAllCountries() =
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, AllCountriesFragment.newInstance())
            .commitNow()

    private fun countryPage() =
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, AllCountriesFragment.newInstance())
            .commitNow()
}