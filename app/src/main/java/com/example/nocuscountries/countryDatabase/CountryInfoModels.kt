package com.example.nocuscountries.countryDatabase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "countries")
class CountryInfoModel(

    @PrimaryKey(autoGenerate = true) val id : Int,
    val name : String,
    val alpha2Code : String,
    val alpha3Code : String,
    val capital : String,
    val region : String,
    val subregion : String,
    val population : Int
)