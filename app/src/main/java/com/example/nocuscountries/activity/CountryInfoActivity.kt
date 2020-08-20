package com.example.nocuscountries.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.nocuscountries.ALPHA_CODE
import com.example.nocuscountries.R
import com.example.nocuscountries.TAB
import com.example.nocuscountries.dataClass.CountryInfo
import com.example.nocuscountries.factory.MainViewModelFactory
import com.example.nocuscountries.viewModel.MainViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_country_info.*

class CountryInfoActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private var savedInstanceState: Bundle? = null
    private lateinit var alpha2code: String
    private lateinit var country: CountryInfo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_info)

        this.savedInstanceState = savedInstanceState
        alpha2code = intent.getStringExtra(ALPHA_CODE)

        observeCountry()
    }

    private fun observeCountry() {

        //
        val viewModelFactory = MainViewModelFactory(this, this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        // we will take that and use it find the country we want
        viewModel.countries.observe(this, Observer { allCountries ->
            country = allCountries.single { info -> info.alpha2Code == alpha2code }
            setup()
        })
    }

    private fun setup(){

        // set flag
        Picasso
            .with(this)
            .load("https://www.countryflags.io/${country.alpha2Code}/shiny/64.png")
            .into(flagImageView)

        countryNameTextView.text = country.name

        nativeNameTextView.text = "The native name is ${country.nativeName}"

        alphaCodeTextView.text = "The country code is ${country.alpha2Code} or ${country.alpha3Code}"

        capitalTextView.text = "The capital of ${country.name} is ${country.capital}"

        regionTextView.text = "It is found in ${country.region}"

        subregionTextView.text = "Specifically ${country.subregion}"

        populationTextView.text = "The population of the coutry is ${country.population}"

        val currencies = country.currencies
        var currenciesStr = "Currencies used includes:\n"
        currencies.forEach {currency ->
            currenciesStr += "${currency.name},$TAB${currency.symbol},$TAB${currency.code};"
        }
        currencyTextView.text = currenciesStr

        val languages = country.languages
        var languagesStr = "Languages spoken:\n"
        languages.forEach {language ->
            languagesStr += "\n${language.name},${TAB}or${TAB}${language.nativeName};"
        }
        languagesTextView.text = languagesStr
    }
}
