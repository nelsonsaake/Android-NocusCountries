package com.example.nocuscountries

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

interface CountryApiService{

    @Get("all")
    fun getCountriesInfo() : Call<ArrayList<CountryInfo>>

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

