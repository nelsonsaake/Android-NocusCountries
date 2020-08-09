package com.example.nocuscountries.countryDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(CountryInfoModel::class), version = 1, exportSchema = false)
public abstract class CountryInfoDatabase : RoomDatabase(){

    abstract fun getCountryInfoDao(): CountryInfoDao

    companion object{

        @Volatile
        private var INSTANCE: CountryInfoDatabase?= null

        private const val DATABASE_NAME = "countries"

        fun getDatabase(context: Context): CountryInfoDatabase {
            if(INSTANCE != null){
                return INSTANCE as CountryInfoDatabase
            }
            synchronized(this){
                INSTANCE =
                    Room.databaseBuilder(
                        context.applicationContext,
                        CountryInfoDatabase::class.java,
                        DATABASE_NAME).build()
                return INSTANCE as CountryInfoDatabase
            }
        }
    }
}