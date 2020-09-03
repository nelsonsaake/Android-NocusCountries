package com.example.nocuscountries.countriesWithSearch

import android.animation.Animator
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.nocuscountries.R
import kotlinx.android.synthetic.main.fragment_countries.*

class CountryWithSearchActivity : AppCompatActivity() {

    private var isShowCountriesFragment: Boolean = true
    private lateinit var viewModel: CountriesWithSearchViewModel
    private lateinit var countriesFragment: CountriesFragment
    private lateinit var searchSettingsFragment: SearchSettingsFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_by_name)

        initVars()
        showCountriesFragment()
    }

    private fun initVars() {
        isShowCountriesFragment = true
        viewModel = ViewModelProvider(this).get(CountriesWithSearchViewModel::class.java)
        countriesFragment = CountriesFragment(viewModel)
        searchSettingsFragment = SearchSettingsFragment(viewModel)
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

            override fun onQueryTextChange(newText: String?): Boolean {

                if(newText.isNullOrEmpty()){
                    return countriesFragment.resetRecyclerViewData()
                }
                return false
            }
        })

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
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