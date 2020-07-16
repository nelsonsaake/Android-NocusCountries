package com.example.nocuscountries

class Currency(
    val code: String?,
    val name: String?,
    val symbol: String?
)

class Language(
    val iso639_1: String,
    val iso639_2: String?,
    val name: String?,
    val nativeName: String?
)

class Translation(
    val de: String?,
    val es: String?,
    val fr: String?,
    val ja: String?,
    val it: String?,
    val br: String?,
    val pt: String?,
    val nl: String?,
    val hr: String?,
    val fa: String?
)

class RegionalBloc(
    val acronym: String?,
    val name: String?,
    val otherAcronyms: ArrayList<String>?,
    val otherNames: ArrayList<String>?
)

data class CountryInfo(
    val name: String?,
    val topLevelDomain: ArrayList<String>?,
    val alpha2Code: String?,
    val alpha3Code: String?,
    val callingCodes: ArrayList<String>?,
    val altSpellings: ArrayList<String>?,
    val region: String?,
    val subregion: String?,
    val population: Int?,
    val latlng: ArrayList<Int>?,
    val demonym: String?,
    val area: Int?,
    val gini: Int?,
    val timezones: ArrayList<String>?,
    val borders: ArrayList<String>?,
    val nativeName: String?,
    val numericCode: String?,
    val currencies: ArrayList<Currency>?,
    val languages: ArrayList<Language>?,
    val translations: ArrayList<Translation>?,
    val flag: String?,
    val regionalBlocs: ArrayList<Translation>?,
    val cioc: String?
)






























