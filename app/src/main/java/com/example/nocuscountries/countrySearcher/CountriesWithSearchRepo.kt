package com.example.nocuscountries.countrySearcher

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.nocuscountries.LOG_TAG
import com.example.nocuscountries.api.CountryApiService
import com.example.nocuscountries.countries.CountriesCache
import com.example.nocuscountries.countries.CountryInfo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CountriesWithSearchRepo {

    private val countryApiService = CountryApiService.create()
    private val cachedData =
        CountriesCache()

    fun getAllCountries(): LiveData<ArrayList<CountryInfo>>? {

        // check cache
        var data = cachedData.getCountries()
        if (data != null) {
            return data
        }

        // check database


        // get from net
        data = MutableLiveData<ArrayList<CountryInfo>>()
        countryApiService.getCountriesInfo().enqueue(object : Callback<ArrayList<CountryInfo>?> {
            override fun onFailure(call: Call<ArrayList<CountryInfo>?>, t: Throwable) {
                Log.e(
                    LOG_TAG,
                    "msg: ${t.localizedMessage}; \n localised msg: ${t.message}\n"
                )
            }

            override fun onResponse(
                call: Call<ArrayList<CountryInfo>?>,
                response: Response<ArrayList<CountryInfo>?>
            ) {
                data.value = response.body()
            }
        })

        cachedData.put(data)
        return data
    }

    fun search(searchString: String): LiveData<ArrayList<CountryInfo>>? {

        val countries = MutableLiveData<ArrayList<CountryInfo>>()

        // we check the cached data

        // we check database

        // we check the internet
        countryApiService.getCountryWithAlpha2Code(searchString).enqueue(object :
            Callback<CountryInfo> {
            override fun onFailure(call: Call<CountryInfo>, t: Throwable) {
                Log.e(
                    LOG_TAG,
                    "msg: ${t.localizedMessage}; \n localised msg: ${t.message}\n"
                )
            }

            override fun onResponse(
                call: Call<CountryInfo>,
                response: Response<CountryInfo>
            ) {
                val tempCountries = arrayListOf(response.body() as CountryInfo)
                countries?.value = tempCountries
                Log.i(
                    LOG_TAG,
                    "response: ${response.body()}\n"
                )
            }

        })

        return countries
    }
}
