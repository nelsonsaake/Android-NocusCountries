package com.example.nocuscountries.adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.nocuscountries.ALPHA_CODE
import com.example.nocuscountries.R
import com.example.nocuscountries.activity.CountryInfoActivity
import com.example.nocuscountries.dataClass.CountryInfo
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso

class CountryListAdapter(private val context: Context,
                         private var countries: ArrayList<CountryInfo> = ArrayList<CountryInfo>()
) :
    RecyclerView.Adapter<CountryListAdapter.ViewHolder>(){

    private val layoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = layoutInflater.inflate(R.layout.country_list_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int = countries.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val countryInfo = countries[position]
        holder.countryNameText?.text = countryInfo.name
        holder.briefDetailsText?.text = "${countryInfo.alpha2Code}\t|\tPopulation: ${countryInfo.population}"
        holder.name = countryInfo.name
        holder.alpha2code = countryInfo.alpha2Code
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

    fun setData(countries: ArrayList<CountryInfo>){
        this.countries = countries
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {

        var name = "Ghana"
        var alpha2code = "gh"
        val countryNameText = itemView?.findViewById<TextView?>(R.id.countryNameText)
        val briefDetailsText = itemView?.findViewById<TextView?>(R.id.briefDetailsText)
        val countryFlag = itemView?.findViewById<ImageView?>(R.id.imageView)

        init {
            itemView?.setOnClickListener {

                // display the country selected name
                Snackbar.make(it, name, Snackbar.LENGTH_LONG).show()

                // display ui presentation of the country selected
                val intent = Intent(context, CountryInfoActivity::class.java)
                intent.putExtra(ALPHA_CODE, alpha2code)
                val options = Bundle()
                options.putString(ALPHA_CODE, alpha2code)
                startActivity(context, intent, options)
            }
        }

    }

}