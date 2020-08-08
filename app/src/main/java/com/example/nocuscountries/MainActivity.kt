package com.example.nocuscountries

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.nocuscountries.ui.main.MainFragment
import kotlinx.android.synthetic.main.landing_fragment.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        if (savedInstanceState == null) {
            viewLandingPage()
        }

        landingButton.setOnClickListener {
            viewAllCountries()
        }
    }

    private fun viewAllCountries() =
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, MainFragment.newInstance())
            .commitNow()

    private fun viewLandingPage() =
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, LandingFragment.newInstance())
            .commitNow()
}