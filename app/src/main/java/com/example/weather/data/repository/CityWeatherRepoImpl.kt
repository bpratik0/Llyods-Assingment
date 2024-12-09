package com.example.weather.data.repository

import com.example.weather.common.NetworkResult
import com.example.weather.common.Util
import com.example.weather.data.api.WeatherAPI
import com.example.weather.data.models.CityWeather
import com.example.weather.domain.repository.CityWeatherRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CityWeatherRepoImpl(private val apiService: WeatherAPI) : CityWeatherRepo {

    private val _categories =
        MutableStateFlow<NetworkResult<CityWeather>>(NetworkResult.Loading())
    val categories: StateFlow<NetworkResult<CityWeather>>
        get() = _categories

    override suspend fun getCityWeather(cityName: String): CityWeather {
        return apiService.getCityWeatherResponse(Util.weather_access_key, cityName)
    }
}