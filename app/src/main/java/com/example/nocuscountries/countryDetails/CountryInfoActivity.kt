package com.example.nocuscountries.countryDetails

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.nocuscountries.ALPHA_CODE
import com.example.nocuscountries.R
import com.example.nocuscountries.TAB
import com.example.nocuscountries.countries.CountryInfo
import com.example.nocuscountries.countries.CountriesModelFactory
import com.example.nocuscountries.countrySearcher.CountriesWithSearchViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_country_details.*

class CountryInfoActivity : AppCompatActivity() {

    private lateinit var viewModel: CountriesWithSearchViewModel
    private var savedInstanceState: Bundle? = null
    private lateinit var alpha2code: String
    private lateinit var country: CountryInfo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_details)

        this.savedInstanceState = savedInstanceState
        alpha2code = intent.getStringExtra(ALPHA_CODE)

        observeCountry()
    }

    private fun observeCountry() {

        //
        val viewModelFactory =
            CountriesModelFactory(
                this,
                this
            )
        viewModel = ViewModelProvider(this).get(CountriesWithSearchViewModel::class.java)

        // we will take that and use it find the country we want
        viewModel.countries?.observe(this, Observer { allCountries ->
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

        alphaCodeTextView.text = " And the short name is ${country.alpha2Code} or ${country.alpha3Code}"

        capitalTextView.text = "The capital of ${country.name} is ${country.capital}"

        regionTextView.text = "It is found in ${country.region}"

        subregionTextView.text = "Specifically ${country.subregion}"

        populationTextView.text = "There are ${country.population} people in ${country.name}"

        val currencies = country.currencies
        var currenciesStr = "Currencies:\n"
        currencies.forEachIndexed { index, currency ->
            currenciesStr += "${currency.name}, ${currency.symbol}, ${currency.code}"
            if(index != currencies.lastIndex){
                currenciesStr += "${TAB}|${TAB}"
            }
        }
        currencyTextView.text = currenciesStr

        val languages = country.languages
        var languagesStr = "Languages spoken:\n"
        languages.forEachIndexed {index, language ->
            languagesStr += "${language.name} or ${language.nativeName}"
            if(index != languages.lastIndex){
                languagesStr += "${TAB}|${TAB}"
            }
        }
        languagesTextView.text = languagesStr
    }
}
