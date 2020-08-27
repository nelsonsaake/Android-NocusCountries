package com.example.nocuscountries.countriesWithSearch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.nocuscountries.*
import kotlinx.android.synthetic.main.fragment_search_settings.*

class SearchSettingsFragment(
    private var viewModel: CountriesWithSearchViewModel
) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    fun setFlag(flag: Int) {

        viewModel.searchOptions = viewModel.searchOptions or flag
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        val searchOptionSwitchList = mapOf(
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

        searchOptionSwitchList.keys.forEach { switch ->
            if (switch.isChecked) setFlag(searchOptionSwitchList[switch] as Int)
        }
    }
}