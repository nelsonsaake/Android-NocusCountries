package com.example.nocuscountries.ui.main

import com.example.nocuscountries.CountryRepo
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.nocuscountries.CountryInfo

class MainViewModel(
    private val countryRepo: CountryRepo
) : ViewModel() {

    var countries : LiveData<ArrayList<CountryInfo>> = countryRepo.refreshCountryInfoData()
}