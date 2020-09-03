package com.example.nocuscountries.countriesWithSearch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import androidx.fragment.app.Fragment
import com.example.nocuscountries.*
import kotlinx.android.synthetic.main.fragment_search_settings.*

class SearchSettingsFragment(
    private var viewModel: CountriesWithSearchViewModel
) : Fragment() {

    private lateinit var searchOptionSwitchList: Map<Switch, Int>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mapSwitches()
        defaultSwitches()
        setupListeners()
    }

    private fun mapSwitches() {

        searchOptionSwitchList = mapOf(
            nameSwitch to NAME_SEARCH_OPTION,
            codeSwitch to CODE_SEARCH_OPTION,
            fullNameSwitch to FULL_NAME_SEARCH_OPTION,
            codeListSwitch to LIST_OF_CODES_SEARCH_OPTION,
            currencySwitch to CURRENCY_SEARCH_OPTION,
            languageSwitch to LANGUAGE_SEARCH_OPTION,
            capitalCitySwitch to CAPITAL_CITY_SEARCH_OPTION,
            callingCodeSwitch to CALLING_CODE_SEARCH_OPTION,
            regionSwitch to REGION_SEARCH_OPTION,
            regionBlocSwitch to REGION_BLOC_SEARCH_OPTION
        )
    }


    private fun defaultSwitches() {

        searchOptionSwitchList.keys.forEach { switch ->

            val flag = searchOptionSwitchList[switch] as Int
            switch.isChecked =  (viewModel.searchOptions and flag) == flag
        }
    }

    fun setFlag(flag: Int) {

        // if the flag is set, the corresponding options corresponding will be set
        viewModel.searchOptions = viewModel.searchOptions or flag

        // in the case where the flag was previously set and the flag is not now
        // we have a '1' and '0'
        // if we 'or' this and store in the options
        // it won't clear the bit in the options flag
        // if we 'and' after the 'or' and the flag is zero it will clear the bit
        //
        // if the flag is '1'
        // and the bit in options is '0'
        // if we or and store in options, options will have that bit to be '1'
        // if we 'and' after and store in options, the bit will persist
        // we = me, my computer, the code and now you :)
        viewModel.searchOptions = viewModel.searchOptions and flag
    }

    private fun setupListeners() {

        searchOptionSwitchList.keys.forEach { switch ->

            switch.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) setFlag(searchOptionSwitchList[switch] as Int)
            }
        }
    }
}