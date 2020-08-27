package com.example.nocuscountries.api

import com.example.nocuscountries.countries.CountryInfo
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CountryApiService{

    @GET("all?fields=name;capital;alpha2Code")
    fun getAllCountries() : Call<ArrayList<CountryInfo>>

    @GET("alpha/{code}")
    fun getCountryWithAlphaCode(
        @Path("code") code: String
    ) : Call<CountryInfo>

    @GET("name/{name}")
    fun getCountriesWithName(
        @Path("name") name: String
    ) : Call<ArrayList<CountryInfo>>

    @GET("name/{name}?fullText=true")
    fun getCountriesWithFullName(
        @Path("name") name: String
    ) : Call<ArrayList<CountryInfo>>

    @GET("alpha?codes={codes}")
    fun getCountriesWithCodes(
        @Query("codes") codes: String
    ) : Call<ArrayList<CountryInfo>>

    @GET("currency/{currency}")
    fun getCountriesWithCurrency(
        @Path("currency") currency: String
    ) : Call<ArrayList<CountryInfo>>

    @GET("lang/{et}")
    fun getCountriesWithLang(
        @Path("et") et: String
    ) : Call<ArrayList<CountryInfo>>

    @GET("capital/{capital}")
    fun getCountriesWithCapital(
        @Path("capital") capital: String
    ) : Call<ArrayList<CountryInfo>>

    @GET("callingcode/{callingcode}")
    fun getCountriesWithCallingCode(
        @Path("callingcode") callingcode: String
    ) : Call<ArrayList<CountryInfo>>

    @GET("region/{region}")
    fun getCountriesWithRegion(
        @Path("region") region: String
    ) : Call<ArrayList<CountryInfo>>

    @GET("regionalbloc/{regionalbloc}")
    fun getCountriesWithRegionalBloc(
        @Path("regionalbloc") regionalbloc: String
    ) : Call<ArrayList<CountryInfo>>

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

