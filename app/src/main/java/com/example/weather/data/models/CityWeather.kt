package com.example.weather.data.models

import com.google.gson.annotations.SerializedName

data class CityWeather(
    @SerializedName("request") var request: Request? = Request(),
    @SerializedName("location") var location: Location? = Location(),
    @SerializedName("current") var current: Current? = Current()
)