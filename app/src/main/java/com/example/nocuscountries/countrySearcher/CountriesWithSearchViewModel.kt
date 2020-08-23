package com.example.nocuscountries.countrySearcher

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.nocuscountries.countries.CountryInfo


class CountriesWithSearchViewModel : ViewModel() {

    private val repo = CountriesWithSearchRepo()

    var countries: LiveData<ArrayList<CountryInfo>>? = repo.getAllCountries()

    fun search(searchString: String): LiveData<ArrayList<CountryInfo>>? = repo.search(searchString)
}
