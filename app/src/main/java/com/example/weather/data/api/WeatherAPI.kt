package com.example.weather.data.api

import com.example.weather.data.models.CityWeather
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {

    @GET("current")
    suspend fun getCityWeatherResponse(
        @Query("access_key") access_key: String,
        @Query("query") city: String
    ): CityWeather
}