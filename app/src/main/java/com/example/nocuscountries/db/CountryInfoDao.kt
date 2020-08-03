package com.example.nocuscountries.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
interface CountryInfoDao {

//    @Query("SELECT * FROM countries")
//    fun getAll() : LiveData<ArrayList<CountryInfoModel>>
//
//    @Query("DELETE FROM countries")
//    suspend fun deleteAll()
}