package com.example.nocuscountries.landingPage

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.nocuscountries.R
import com.example.nocuscountries.countrySearcher.CountryWithSearchActivity
import com.example.nocuscountries.countries.CountriesActivity
import kotlinx.android.synthetic.main.content_landing_page.*

class LandingPageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing_page)

        setupButtons()
    }

    private fun setupButtons() {

        allButton.setOnClickListener {
            startActivity(Intent(applicationContext, CountriesActivity::class.java))
        }

        exploreButton.setOnClickListener {
            startActivity(Intent(applicationContext, CountryWithSearchActivity::class.java))
        }
    }
}