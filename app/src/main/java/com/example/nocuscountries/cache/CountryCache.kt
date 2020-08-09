package com.example.nocuscountries.cache

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.nocuscountries.dataClasses.CountryInfo

class CountryCache {

    private var countriesInfo: MutableLiveData<ArrayList<CountryInfo>>?

    init {
        countriesInfo = null
    }

    fun getCountries(): LiveData<ArrayList<CountryInfo>>? {
        return countriesInfo
    }

    fun put(data: MutableLiveData<java.util.ArrayList<CountryInfo>>) {
        countriesInfo = data
    }
}
