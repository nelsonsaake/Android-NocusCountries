package com.example.nocuscountries

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.nocuscountries.ui.main.MainViewModel

class MainViewModelFactory(
    private val context: Context,
    private val lifecycleOwner: LifecycleOwner
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        val countryApiService = CountryApiService.create()
        val countryCache = CountryCache()

        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {

            val countryRepo = CountryRepo(
                countryApiService,
                countryCache,
                context.applicationContext,
                lifecycleOwner
            )
            return MainViewModel(countryRepo) as T
        }
        throw IllegalArgumentException("the something went ....")
    }
}

