package com.example.nocuscountries.countries

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class CountriesViewModel(
    private val countriesRepo: CountriesRepo
) : ViewModel() {

    var countries : LiveData<ArrayList<CountryInfo>> = countriesRepo.refreshCountryInfoData()
}