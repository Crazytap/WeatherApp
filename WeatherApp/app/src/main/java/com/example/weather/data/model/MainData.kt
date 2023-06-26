package com.example.weather.data.model

import com.google.gson.annotations.SerializedName

data class MainData(
    @SerializedName("temp") val temperature: Float,
    val pressure: Int,
    val humidity: Int
)
