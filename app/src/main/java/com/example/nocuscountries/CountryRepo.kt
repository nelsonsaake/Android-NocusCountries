package com.example.nocuscountries

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CountryRepo @Inject constructor(
    private val countryApiService : CountryApiService,
    private val countryCache : CountryCache
) {

    fun getCountries() : LiveData<ArrayList<CountryInfo>> {

        val cached : LiveData<ArrayList<CountryInfo>> = countryCache.getCountries()
        if (cached != null) return cached

        val data = MutableLiveData<ArrayList<CountryInfo>>()
        countryCache.put(data)

        countryApiService.getCountriesInfo().enqueue(object : Callback<ArrayList<CountryInfo>> {

            override fun onFailure(call: Call<ArrayList<CountryInfo>>, t: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onResponse(
                call: Call<ArrayList<CountryInfo>>,
                response: Response<ArrayList<CountryInfo>>
            ) {
                data.value = response.body()
            }
        })
        return data
    }

}





















//
//val countryApiService by lazy {
//    CountryApiService.create()
//}
//
//var disposable: Disposable? = null

//
//fun getCountriesDataFromApi(){
//    disposable =
//        countryApiService.getCountriesInfo()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(
//                { result -> countries},
//                { error -> err = error.message}
//            )
//}
