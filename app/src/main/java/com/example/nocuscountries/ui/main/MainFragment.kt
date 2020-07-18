package com.example.nocuscountries.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.*
import com.example.nocuscountries.*
import com.example.nocuscountries.R
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : androidx.fragment.app.Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    fun dothething(){

        val viewModelFactory = ViewModelFactory()
        viewModel = ViewModelProvider(
            this, viewModelFactory).get(MainViewModel::class.java)

        // setup live data something for when we get data back from the apic
        viewModel.countries.observe(viewLifecycleOwner, Observer {
                message.text = viewModel.countries.value.toString()
                Log.i("NocusCountries", "login commit changes!\n")
            }
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dothething()
    }
}



