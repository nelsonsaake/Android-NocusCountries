package com.example.nocuscountries

import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val countryApiService by lazy {
    CountryApiService.create()
}

var disposable: Disposable? = null

interface CountryApiService{

    @Get("all")
    fun getCountriesInfo() : Observable<CountryInfo>

    annotation class Get(val endpoint: String)

    companion object {
        fun create(): CountryApiService {

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://restcountries.eu/rest/v2/")
                .build()

            return retrofit.create(CountryApiService::class.java)
        }
    }
}

