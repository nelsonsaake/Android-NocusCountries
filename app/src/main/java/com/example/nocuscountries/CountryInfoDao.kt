package com.example.nocuscountries

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CountryInfoDao {

    @Insert
    suspend fun insert(countries: ArrayList<CountryInfo>)

    @Query("SELECT * FROM CountryInfo")
    fun getCountries() : LiveData<ArrayList<CountryInfo>>

    @Query("DELETE FROM CountryInfo")
    suspend fun  delete()
}