package com.example.weather.data.model

data class WeatherResponse(
    val weather: List<WeatherData>,
    val main: MainData
)