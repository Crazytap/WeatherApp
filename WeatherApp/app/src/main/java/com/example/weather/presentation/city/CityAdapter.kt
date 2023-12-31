package com.example.weather.presentation.city

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.R
import com.example.weather.presentation.model.City

class CityAdapter (private val cities: List<City>,
                   private val cityListener: CityItemListener
)
    : RecyclerView.Adapter<CityAdapter.ViewHolder>(), View.OnClickListener {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val cardView = itemView.findViewById<CardView>(R.id.card_view)!!
        val cityNameView = itemView.findViewById<TextView>(R.id.name)!!
        val deleteView = itemView.findViewById<View>(R.id.delete)!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewItem = LayoutInflater.from(parent?.context)
            .inflate(R.layout.item_city, parent, false)
        return ViewHolder(viewItem)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
val city = cities[position]
        with(holder) {
            cardView.setOnClickListener(this@CityAdapter)
            cardView.tag = city
            cityNameView.text = city.name
            deleteView.tag = city
            deleteView.setOnClickListener(this@CityAdapter)
        }
    }

    override fun getItemCount(): Int = cities.size
    override fun onClick(view: View) {
        when(view.id) {
            R.id.card_view -> cityListener.onCitySelected(view.tag as City)
            R.id.delete -> cityListener.onCityDeleted(view.tag as City)
        }
    }

}