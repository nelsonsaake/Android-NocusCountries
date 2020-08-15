package com.example.nocuscountries.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nocuscountries.dataClass.CountryInfo

class CountryByNameViewModel: ViewModel() {

    lateinit var countries : MutableLiveData<ArrayList<CountryInfo>>
}