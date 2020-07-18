package com.example.nocuscountries

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class CountryRecyclerAdapter(private val context: Context,
                             private val countries: ArrayList<CountryInfo>) :
    RecyclerView.Adapter<CountryRecyclerAdapter.ViewHolder>(){
    private val layoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = layoutInflater.inflate(R.layout.country_list_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int = countries.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val countryInfo = countries[position]
        holder.countryNameText?.text = countryInfo.name
        holder.briefDetailsText?.text = "Capital: ${countryInfo.capital} | Population: ${countryInfo.population}"
        holder.countryName = countryInfo.name
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
    }
}