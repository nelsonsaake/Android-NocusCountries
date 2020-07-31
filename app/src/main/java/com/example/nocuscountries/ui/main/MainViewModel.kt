package com.example.nocuscountries.ui.main

import CountryRepo
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.nocuscountries.CountryInfo

class MainViewModel(
    countryRepo: CountryRepo
) : ViewModel() {

    var countries : LiveData<ArrayList<CountryInfo>> = countryRepo.getCountries()
}