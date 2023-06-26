package com.example.weather.data

import com.example.weather.data.cache.CityPreferences
import com.example.weather.data.cloud.RetrofitInstance
import com.example.weather.data.model.WeatherResponse
import com.example.weather.presentation.model.City

class Repository {

    private val api = RetrofitInstance.weatherService
    private val cityPreferences = CityPreferences()

    suspend fun getWeather(city: City): WeatherResponse? {
        return try {
            api.getWeather(city.name)
        } catch (e: Exception) {
            e
            null
        }
    }

    fun getAllCities(): List<City> {
        return cityPreferences.getAllCities()
    }

    fun saveCity(city: City) {
        cityPreferences.saveCity(city)
    }

    fun deleteCity(city: City) {
        cityPreferences.deleteCity(city)
    }
}