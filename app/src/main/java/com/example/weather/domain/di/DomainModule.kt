package com.example.weather.domain.di

import com.example.weather.domain.repository.CityWeatherRepo
import com.example.weather.domain.use_cases.GetCityCurrentWeatherUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object DomainModule {

    @Provides
    fun provideGetCityWeatherDetailsUseCase(cityWeatherRepo: CityWeatherRepo): GetCityCurrentWeatherUseCase {
        return GetCityCurrentWeatherUseCase(cityWeatherRepo = cityWeatherRepo)
    }

}