package com.example.weather.presentation.city

import com.example.weather.presentation.model.City

interface  CityItemListener{
    fun onCitySelected(city: City)
    fun onCityDeleted(city: City)
}