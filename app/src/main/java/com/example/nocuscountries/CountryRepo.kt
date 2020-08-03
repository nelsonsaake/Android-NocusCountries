package com.example.nocuscountries

import android.content.Context
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.nocuscountries.CountryApiService
import com.example.nocuscountries.CountryCache
import com.example.nocuscountries.CountryInfo
import com.example.nocuscountries.db.CountryInfoDatabase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CountryRepo(
    private val countryApiService: CountryApiService,
    private val countryCache: CountryCache,
    private val context: Context,
    private val lifecycleOwner: LifecycleOwner
) {

    fun refreshCountryInfoData(): LiveData<ArrayList<CountryInfo>> {

        // check in-memory - cache
        val cached = countryCache.getCountries()
        if (cached != null) return cached

        // check the db


        // pull from api
        val data = MutableLiveData<ArrayList<CountryInfo>>()

        // add to cache
        countryCache.put(data)

        //

        countryApiService.getCountriesInfo().enqueue(object : Callback<ArrayList<CountryInfo>> {

            override fun onFailure(call: Call<ArrayList<CountryInfo>>, t: Throwable) {
                Log.e(
                    "NocusCountries",
                    "msg: ${t.localizedMessage}; \n localised msg: ${t.message}\n"
                )
            }

            override fun onResponse(
                call: Call<ArrayList<CountryInfo>>,
                response: Response<ArrayList<CountryInfo>>
            ) {
                data.value = response.body()
                Log.i("NocusCountries", "country response from http api call\n")
            }
        })

        return data
    }
}
