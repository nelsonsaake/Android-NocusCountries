package com.example.nocuscountries.countriesWithSearch

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.nocuscountries.*
import com.example.nocuscountries.api.CountryApiService
import com.example.nocuscountries.countries.CountryInfo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CountriesWithSearchRepo {

    var searchOptions: Int = ALL_SEARCH_OPTION
    var data = MutableLiveData<ArrayList<CountryInfo>>()
    private val countryApiService = CountryApiService.create()
    private val searchResult = MutableLiveData<ArrayList<CountryInfo>>()

    private fun onFailure(t: Throwable) {
        Log.e(
            LOG_TAG,
            "msg: ${t.localizedMessage}; \n localised msg: ${t.message}\n"
        )
    }

    private fun setSearchResult(countries: ArrayList<CountryInfo>) {

        searchResult.value = countries
        Log.i(
            LOG_TAG,
            "response: ${countries}\n"
        )
    }

    fun onResponse(response: Response<CountryInfo>) {

        setSearchResult(arrayListOf(response.body() as CountryInfo))
    }

    private fun onArrayResponse(response: Response<ArrayList<CountryInfo>>) {

        if(response?.body() == null) return

        setSearchResult(response.body()!!)
    }

    private fun onCountriesResponse(response: Response<ArrayList<CountryInfo>>) {

        data.value = response.body()
    }

    private val countriesCallbackObject = object : Callback<ArrayList<CountryInfo>> {

        override fun onFailure(call: Call<ArrayList<CountryInfo>>, t: Throwable) = onFailure(t)

        override fun onResponse(
            call: Call<ArrayList<CountryInfo>>,
            response: Response<ArrayList<CountryInfo>>
        ) = onCountriesResponse(response)
    }

    private val arrayCallbackObject = object : Callback<ArrayList<CountryInfo>> {

        override fun onFailure(call: Call<ArrayList<CountryInfo>>, t: Throwable) = onFailure(t)

        override fun onResponse(
            call: Call<ArrayList<CountryInfo>>,
            response: Response<ArrayList<CountryInfo>>
        ) = onArrayResponse(response)
    }

    private val callbackObject = object : Callback<CountryInfo> {

        override fun onFailure(call: Call<CountryInfo>, t: Throwable) = onFailure(t)
        override fun onResponse(call: Call<CountryInfo>, response: Response<CountryInfo>) =
            onResponse(response)
    }

    fun getAllCountries(): MutableLiveData<ArrayList<CountryInfo>> {

        countryApiService
            .getAllCountries()
            .enqueue(countriesCallbackObject)

        return data
    }

    private fun getCountryWithAlphaCode(alphaCode: String) {

        countryApiService
            .getCountryWithAlphaCode(alphaCode)
            .enqueue(callbackObject)
    }

    private fun getCountriesWithName(name: String) {

        countryApiService
            .getCountriesWithName(name)
            .enqueue(arrayCallbackObject)
    }

    private fun getCountriesWithFullName(name: String) {

        countryApiService
            .getCountriesWithFullName(name)
            .enqueue(arrayCallbackObject)
    }

    private fun getCountriesWithCodes(codes: String) {

        countryApiService
            .getCountriesWithCodes(codes)
            .enqueue(arrayCallbackObject)
    }

    private fun getCountriesWithCurrency(currency: String) {

        countryApiService
            .getCountriesWithCurrency(currency)
            .enqueue(arrayCallbackObject)
    }

    private fun getCountriesWithLang(et: String) {

        countryApiService
            .getCountriesWithLang(et)
            .enqueue(arrayCallbackObject)
    }

    private fun getCountriesWithCapital(capital: String) {

        countryApiService
            .getCountriesWithCapital(capital)
            .enqueue(arrayCallbackObject)
    }

    private fun getCountriesWithCallingCode(callingcode: String) {

        countryApiService
            .getCountriesWithCapital(callingcode)
            .enqueue(arrayCallbackObject)
    }

    private fun getCountriesWithRegion(region: String) {

        countryApiService
            .getCountriesWithRegion(region)
            .enqueue(arrayCallbackObject)
    }

    private fun getCountriesWithRegionalBloc(regionalbloc: String) {

        countryApiService
            .getCountriesWithRegionalBloc(regionalbloc)
            .enqueue(arrayCallbackObject)
    }

    fun search(searchString: String): LiveData<ArrayList<CountryInfo>>? {

        /*
        *
        * In another implementation:
        * create the callback objects, but the point where they are enqueued,
        * put them all in an arraylist and forEach the list to enqueue them
        *
        * This would reduce massively the function in the repo :)
        *
        * */

        val flagApiTbl = mapOf<Int, (searchString: String) -> Unit>(
            CODE_SEARCH_OPTION to ::getCountryWithAlphaCode,
            NAME_SEARCH_OPTION to ::getCountriesWithName,
            FULL_NAME_SEARCH_OPTION to ::getCountriesWithFullName,
            LIST_OF_CODES_SEARCH_OPTION to ::getCountriesWithCodes,
            CURRENCY_SEARCH_OPTION to ::getCountriesWithCurrency,
            LANGUAGE_SEARCH_OPTION to ::getCountriesWithLang,
            CAPITAL_CITY_SEARCH_OPTION to ::getCountriesWithCapital,
            CALLING_CODE_SEARCH_OPTION to ::getCountriesWithCallingCode,
            REGION_SEARCH_OPTION to ::getCountriesWithRegion,
            REGION_BLOC_SEARCH_OPTION to ::getCountriesWithRegionalBloc
        )

        flagApiTbl.keys.forEach { flag ->

            if ((flag and searchOptions) == flag) {
                val api = flagApiTbl[flag]
                api?.invoke(searchString)
            }
        }

        return searchResult
    }

    fun getCountryWithAlpha2Code(code: String): LiveData<CountryInfo> {

        val res = MutableLiveData<CountryInfo>()

        countryApiService
            .getCountryWithAlphaCode(code)
            .enqueue(object : Callback<CountryInfo> {

                override fun onFailure(call: Call<CountryInfo>, t: Throwable) = onFailure(t)
                override fun onResponse(call: Call<CountryInfo>, response: Response<CountryInfo>) {

                    res.value = response.body()
                }
            })

        return res
    }
}
