package com.example.nocuscountries.countries

import android.content.Context
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.nocuscountries.LOG_TAG
import com.example.nocuscountries.api.CountryApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CountriesRepo(
    private val countryApiService: CountryApiService,
    private val countriesCache: CountriesCache,
    private val context: Context,
    private val lifecycleOwner: LifecycleOwner
) {

    fun refreshCountryInfoData(): LiveData<ArrayList<CountryInfo>> {

        // check in-memory - cache
        val cached = countriesCache.getCountries()
        if (cached != null) return cached

        // check the db

        // pull from api
        val data = MutableLiveData<ArrayList<CountryInfo>>()

        // add to cache
        countriesCache.put(data)

        //
        countryApiService.getAllCountries().enqueue(object : Callback<ArrayList<CountryInfo>> {

            override fun onFailure(call: Call<ArrayList<CountryInfo>>, t: Throwable) {
                Log.e(
                    LOG_TAG,
                    "msg: ${t.localizedMessage}; \n localised msg: ${t.message}\n"
                )
            }

            override fun onResponse(
                call: Call<ArrayList<CountryInfo>>,
                response: Response<ArrayList<CountryInfo>>
            ) {
                data.value = response.body()
                Log.i(LOG_TAG, "country response from http api call\n")
            }
        })

        return data
    }
}
