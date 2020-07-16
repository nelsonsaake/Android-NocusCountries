package com.example.nocuscountries.bjt

import androidx.lifecycle.LiveData
import com.example.nocuscountries.CountryInfo
import com.example.nocuscountries.countryApiService
import com.example.nocuscountries.disposable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object DataManager {

    var countriesx : LiveData<ArrayList<CountryInfo>> = TODO()
    var errx : String? = null

    private fun getCountriesDataFromApi(){
        disposable =
            countryApiService.getCountriesInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { result -> countriesx },
                    { error -> errx = error.message}
                )
    }

}