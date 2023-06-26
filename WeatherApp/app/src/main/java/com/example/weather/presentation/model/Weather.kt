package com.example.weather.presentation.model

data class Weather(
    val description: String,
    val temperature: Float,
    val pressure: Int,
    val iconUrl: String,
    val humidity: Int
)
