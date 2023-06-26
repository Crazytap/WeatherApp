package com.example.weather.presentation.city

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weather.data.Repository
import com.example.weather.presentation.model.City

class CityViewModel : ViewModel() {

    private val repository = Repository()

    val cityLiveData = MutableLiveData<List<City>>()

    fun getAllSavedCities() {
        val cities = repository.getAllCities()
        cityLiveData.value = cities
    }

    fun saveCity(city: City) {
        repository.saveCity(city)
    }

    fun deleteCity(city: City) {
        repository.deleteCity(city)
    }
}