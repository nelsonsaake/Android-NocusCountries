package com.example.nocuscountries.countryDetails

import android.os.Bundle
import android.text.Html
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.nocuscountries.ALPHA_CODE
import com.example.nocuscountries.R
import com.example.nocuscountries.TAB
import com.example.nocuscountries.countries.CountriesModelFactory
import com.example.nocuscountries.countries.CountryInfo
import com.example.nocuscountries.countries.Currencies
import com.example.nocuscountries.countries.Languages
import com.example.nocuscountries.countriesWithSearch.CountriesWithSearchViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_country_details.*

class CountryDetails : AppCompatActivity() {

    private lateinit var viewModel: CountriesWithSearchViewModel
    private var savedInstanceState: Bundle? = null
    private lateinit var alpha2code: String
    private var country: CountryInfo? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_details)

        this.savedInstanceState = savedInstanceState
        alpha2code = intent.getStringExtra(ALPHA_CODE)

        setupFlag()
        observeCountry()
    }

    private fun observeCountry() {

        viewModel = ViewModelProvider(this).get(CountriesWithSearchViewModel::class.java)
        viewModel.getCountryWithAlpha2Code(alpha2code).observe(this, Observer {

            country = it
            setup(it)
        })
    }

    private fun makeHtml(text: String, style: String): String {

        return "<span style=$style>$text</span>"
    }

    private fun makeNameTypeSpan(name: String): String {

        return "<span> $name </span>"
    }

    private fun makeValueTypeSpan(value: String): String {

        val style = "color: #555555; font-size: 2rem;"
        val span = makeHtml(value, style)
        return "$span<br/>"
    }

    private fun concat(left: String, right: String): String {

        return left + right
    }

    private fun currenciesToHtml(currencies: List<Currencies>): String {

        var nameHtml = makeNameTypeSpan("Currencies:\n")

        var valStr = ""
        currencies.forEachIndexed { index, currency ->
            valStr += "${currency.name}, ${currency.symbol}, ${currency.code}"
            if (index != currencies.lastIndex) {
                valStr += "${TAB}|${TAB}"
            }
        }

        var valueHtml = makeValueTypeSpan(valStr)
        val style = "background-color: "
        valueHtml = makeHtml(valueHtml, style)

        return concat(nameHtml, valueHtml)
    }

    private fun languagesToHtml(languages: List<Languages>): String {

        var nameHtml = makeNameTypeSpan("Languages spoken:\n")

        var valStr = ""
        languages.forEachIndexed { index, language ->
            valStr += "${language.name} or ${language.nativeName}"
            if (index != languages.lastIndex) {
                valStr += "${TAB}|${TAB}"
            }
        }

        var valueHtml = makeValueTypeSpan(valStr)
        val style = "background-color: "
        valueHtml = makeHtml(valueHtml, style)

        return concat(nameHtml, valueHtml)
    }

    private fun countryToHtml(country: CountryInfo): String {

        var countryHtml = ""

        val map = mapOf<String, String>(
            "Name: " to country.name,
            "Native name: " to country.nativeName,
            "Alpha2Code: " to "${country.alpha2Code} or ${country.alpha3Code}",
            "Capital: " to "${country.capital}",
            "Region: " to "${country.region}",
            "Sub-region: " to "${country.subregion}",
            "Population: " to " ${country.population}"
        )

        map.forEach {

            val nameHtml = makeNameTypeSpan(it.key)
            val valueHtml = makeValueTypeSpan(it.value + "\n")

            countryHtml += "$nameHtml$valueHtml"
        }

        countryHtml += currenciesToHtml(country.currencies)
        countryHtml += languagesToHtml(country.languages)

        return countryHtml
    }

    private fun setupFlag(){

        // set flag
        Picasso
            .with(this)
            .load("https://www.countryflags.io/${alpha2code}/shiny/64.png")
            .into(flagImageView)

    }

    private fun setup(country: CountryInfo) {

        countryDetailsTextView.text = Html.fromHtml(countryToHtml(country))
    }
}
