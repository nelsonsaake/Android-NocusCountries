package com.example.nocuscountries

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.nocuscountries.ui.main.MainViewModel

class ViewModelFactory(): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val countryApiService = CountryApiService.create()
        val countryCache = CountryCache()

        if(modelClass.isAssignableFrom(MainViewModel::class.java)){

            return MainViewModel (CountryRepo(countryApiService, countryCache)) as T
        }
        throw IllegalArgumentException("the something went ....")
    }
}

