package com.example.weather.presentation.weather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.data.Repository
import com.example.weather.data.model.WeatherResponse
import com.example.weather.presentation.model.City
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {

    private val repository = Repository()
    val weatherLiveData = MutableLiveData<WeatherResponse?>()

    fun getWeather(city: City) {
        viewModelScope.launch {
            val weather = repository.getWeather(city)
            weatherLiveData.postValue(weather)
        }
    }

}