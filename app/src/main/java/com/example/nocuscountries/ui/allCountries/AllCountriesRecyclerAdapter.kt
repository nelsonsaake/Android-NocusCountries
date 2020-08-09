package com.example.nocuscountries.ui.allCountries

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nocuscountries.R
import com.example.nocuscountries.dataClasses.CountryInfo
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso

class AllCountriesRecyclerAdapter(private val context: Context,
                                  private val countries: ArrayList<CountryInfo>) :
    RecyclerView.Adapter<AllCountriesRecyclerAdapter.ViewHolder>(){

    private val layoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = layoutInflater.inflate(R.layout.main_all_country_list_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int = countries.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val countryInfo = countries[position]
        holder.countryNameText?.text = countryInfo.name
        holder.briefDetailsText?.text = "${countryInfo.alpha2Code}\t|\tPopulation: ${countryInfo.population}"
        holder.countryName = countryInfo.name
        setCountryFlag(holder, countryInfo)
    }

    fun setCountryFlag(holder: ViewHolder, country: CountryInfo){

        /*
         * examples:
         * "https://www.countryflags.io/be/flat/64.png"
         * "https://www.countryflags.io/be/shiny/64.png"
         *
         * template:
         * "https://www.countryflags.io/:country_code/:style/:size.png"
         *
         * use this string to build url for the flag:
         * "https://www.countryflags.io/${alpha2Code}/shiny/64.png"
         */

        Picasso
            .with(context)
            .load("https://www.countryflags.io/${country.alpha2Code}/shiny/64.png")
            .into(holder.countryFlag)
    }

    inner class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {

        var countryName = ""
        init {
            itemView?.setOnClickListener {
                Snackbar.make(it, countryName, Snackbar.LENGTH_LONG).show()
            }
        }

        val countryNameText = itemView?.findViewById<TextView?>(R.id.countryNameText)
        val briefDetailsText = itemView?.findViewById<TextView?>(R.id.briefDetailsText)
        val countryFlag = itemView?.findViewById<ImageView?>(R.id.imageView)
    }
}