package com.example.weather.domain.repository

import com.example.weather.data.models.CityWeather

interface CityWeatherRepo {

    suspend fun getCityWeather(cityName: String): CityWeather

}