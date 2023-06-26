package com.example.weather.data.cache

import android.content.Context
import com.example.weather.core.App
import com.example.weather.presentation.model.City
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CityPreferences {

    companion object {
        private const val PREFERENCES_KEY = "PREFERENCES_KEY"
        private const val CITIES_KEY = "CITIES_KEY"
    }

    private val context: Context = App.instance

    fun getAllCities(): List<City> {
        val json = getSavedCitiesJson()
        return fromJson(json)
    }

    fun saveCity(city: City) {
        val cities = getAllCities().toMutableList()
        cities.add(city)
        saveCitiesToJson(cities)
    }

    fun deleteCity(city: City) {
        val cities = getAllCities().toMutableList()
        cities.remove(city)
        saveCitiesToJson(cities)
    }

    private fun saveCitiesToJson(cities: List<City>) {
        val gson = Gson()
        val json = gson.toJson(cities)
        getSharedPreferences().edit().putString(CITIES_KEY, json).apply()
    }

    private fun getSavedCitiesJson(): String {
        return getSharedPreferences().getString(CITIES_KEY, "") ?: ""
    }

    private fun fromJson(json: String): List<City> {
        val gson = Gson()
        val listType = object : TypeToken<List<City>>() {}.type
        return gson.fromJson(json, listType) ?: emptyList()
    }

    private fun getSharedPreferences() =
        context.getSharedPreferences(PREFERENCES_KEY, Context.MODE_PRIVATE)
}