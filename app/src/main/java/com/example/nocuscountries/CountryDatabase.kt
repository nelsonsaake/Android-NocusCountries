package com.example.nocuscountries

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(CountryInfo::class), version = 1)
abstract class CountresDatabase: RoomDatabase() {

    abstract fun countryInfoDao() : CountryInfoDao

    companion object{
        @Volatile
        private var INSTANCE: CountresDatabase? = null

        fun getDatabase(context: Context): CountresDatabase{

            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }

            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CountresDatabase::class.java,
                    "CountriesDatabase"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}