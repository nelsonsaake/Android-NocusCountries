package com.example.nocuscountries.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.nocuscountries.CountryInfo
import com.example.nocuscountries.CountryRepo

class MainViewModel(
    countryRepo: CountryRepo
) : ViewModel() {

    var countries : LiveData<ArrayList<CountryInfo>> = countryRepo.getCountries()
}