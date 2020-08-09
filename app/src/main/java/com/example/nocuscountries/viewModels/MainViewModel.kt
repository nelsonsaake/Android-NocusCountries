package com.example.nocuscountries.viewModels

import com.example.nocuscountries.repositories.CountryRepo
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.nocuscountries.dataClasses.CountryInfo

class MainViewModel(
    private val countryRepo: CountryRepo
) : ViewModel() {

    var countries : LiveData<ArrayList<CountryInfo>> = countryRepo.refreshCountryInfoData()
}