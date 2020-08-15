package com.example.nocuscountries.viewModel

import com.example.nocuscountries.repository.CountryRepo
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.nocuscountries.dataClass.CountryInfo

class MainViewModel(
    private val countryRepo: CountryRepo
) : ViewModel() {

    var countries : LiveData<ArrayList<CountryInfo>> = countryRepo.refreshCountryInfoData()
}