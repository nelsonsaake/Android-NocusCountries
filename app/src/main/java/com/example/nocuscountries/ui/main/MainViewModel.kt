package com.example.nocuscountries.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.nocuscountries.CountryInfo
import com.example.nocuscountries.countryApiService
import com.example.nocuscountries.disposable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainViewModel : ViewModel() {

    var countries : LiveData<ArrayList<CountryInfo>>? = TODO()
    var err : String? = null

}