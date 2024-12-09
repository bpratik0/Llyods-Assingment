package com.example.weather.data.di

import com.example.weather.data.repository.CityWeatherRepoImpl
import com.example.weather.data.api.WeatherAPI
import com.example.weather.domain.repository.CityWeatherRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("https://api.weatherstack.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideWeatherAPI(retrofit: Retrofit): WeatherAPI {
        return retrofit.create(WeatherAPI::class.java)
    }

    @Provides
    fun provideCityWeatherApiRepo(apiService: WeatherAPI): CityWeatherRepo {
        return CityWeatherRepoImpl(apiService)
    }

}