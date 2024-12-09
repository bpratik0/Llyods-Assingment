package com.example.weather.presentation.viewmodels

import com.example.weather.data.models.CityWeather

data class CurrentCityWeatherDetailsState(
    val isLoading: Boolean = false,
    val error: String = "",
    val data: CityWeather? = null
)
