package com.example.nocuscountries.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nocuscountries.R

class CountryInfoAdapter(val context: Context, val map: Map<String, String>)
    : RecyclerView.Adapter<CountryInfoAdapter.ViewHolder>() {

    inner class ViewHolder(item: View) : RecyclerView.ViewHolder(item){

        val nameText: TextView? = item?.findViewById<TextView>(R.id.nameText)
        val valueText: TextView? = item?.findViewById<TextView>(R.id.valueText)
    }

    private val layoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val item = layoutInflater.inflate(R.layout.country_list_item, parent, false)
        return ViewHolder(item)
    }

    override fun getItemCount() = map.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val name = map.keys.elementAt(position)
        val value = map.values.elementAt(position)
        holder.nameText?.text = name
        holder.valueText?.text = value
    }
}