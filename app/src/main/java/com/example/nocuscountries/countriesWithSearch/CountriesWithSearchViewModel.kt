package com.example.nocuscountries.countriesWithSearch

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.nocuscountries.countries.CountryInfo

class CountriesWithSearchViewModel : ViewModel() {

    var searchOptions: Int
        get(){
            return repo.searchOptions
        }
        set(value){
            repo.searchOptions = value
        }

    private val repo = CountriesWithSearchRepo()

    var countries: LiveData<ArrayList<CountryInfo>>? = repo.getAllCountries()

    fun search(searchString: String): LiveData<ArrayList<CountryInfo>>? = repo.search(searchString)
}

