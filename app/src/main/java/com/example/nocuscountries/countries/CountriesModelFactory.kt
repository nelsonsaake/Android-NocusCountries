package com.example.nocuscountries.countries

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.nocuscountries.api.CountryApiService

class CountriesModelFactory(
    private val context: Context,
    private val lifecycleOwner: LifecycleOwner
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        val countryApiService = CountryApiService.create()
        val countryCache = CountriesCache()

        if (modelClass.isAssignableFrom(CountriesViewModel::class.java)) {

            val countryRepo =
                CountriesRepo(
                    countryApiService,
                    countryCache,
                    context.applicationContext,
                    lifecycleOwner
                )
            return CountriesViewModel(
                countryRepo
            ) as T
        }
        throw IllegalArgumentException("the something went ....")
    }
}

