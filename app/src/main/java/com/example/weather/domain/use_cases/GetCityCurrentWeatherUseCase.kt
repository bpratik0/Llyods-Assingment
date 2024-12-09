package com.example.weather.domain.use_cases

import com.example.weather.common.NetworkResult
import com.example.weather.data.models.CityWeather
import com.example.weather.domain.repository.CityWeatherRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetCityCurrentWeatherUseCase(private val cityWeatherRepo: CityWeatherRepo) {

    operator fun invoke(cityName: String): Flow<NetworkResult<CityWeather>> = flow {
        emit(NetworkResult.Loading())
        val result = cityWeatherRepo.getCityWeather(cityName)
        if (result.request?.type != null) {
            emit(NetworkResult.Success(result))
        } else {
            emit(NetworkResult.Error("Something Went Wrong"))
        }

    }.flowOn(Dispatchers.IO)

}