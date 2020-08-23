package com.example.nocuscountries.countrySearcher

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import com.example.nocuscountries.R

class CountryWithSearchActivity : AppCompatActivity() {

    private var isShowCountriesFragment = true
    private val countriesFragment = CountriesFragment()
    private val searchSettingsFragment = SearchSettingsFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_by_name)

        showCountriesFragment()
    }

    private fun showCountriesFragment() =
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, countriesFragment)
            .commitNow()

    private fun showSettingsFragment() =
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, searchSettingsFragment)
            .commitNow()

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu_countries_with_search, menu)

        val menuItem = menu?.findItem(R.id.search_menu_item)
        val searchView = menuItem?.actionView as SearchView

        searchView.isIconifiedByDefault = false
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {

                return countriesFragment.search(searchView.query.toString())
            }

            override fun onQueryTextChange(newText: String?) = false
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
             R.id.search_settings_menu_item -> toggleFragment()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun toggleFragment() {

        isShowCountriesFragment = !isShowCountriesFragment

        if (isShowCountriesFragment) showCountriesFragment()
        else showSettingsFragment()
    }
}