package com.example.weather.data

import com.example.weather.data.model.WeatherResponse
import com.example.weather.presentation.model.Weather

fun mapOpenWeatherDataToWeather(weatherResponse: WeatherResponse): Weather {
    val weatherFirst = weatherResponse.weather.first()
    return Weather(
        description = weatherFirst.description,
        temperature = weatherResponse.main.temperature,
        humidity = weatherResponse.main.humidity,
        pressure = weatherResponse.main.pressure,
        iconUrl = "https://openweathermap.org/img/w/${weatherFirst.icon}.png"
    )
}