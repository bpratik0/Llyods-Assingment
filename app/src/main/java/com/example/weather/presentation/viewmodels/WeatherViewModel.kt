package com.example.weather.presentation.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.common.NetworkResult
import com.example.weather.domain.use_cases.GetCityCurrentWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val getCityCurrentWeatherUseCase: GetCityCurrentWeatherUseCase
) : ViewModel() {

    init {
        getCityWeatherDetails("Mumbai")
    }

    private val _currentWeather = mutableStateOf(CurrentCityWeatherDetailsState())
    val currentWeather: State<CurrentCityWeatherDetailsState> = _currentWeather


    fun getCityWeatherDetails(cityName: String) {
        getCityCurrentWeatherUseCase(cityName).onEach {
            when (it) {
                is NetworkResult.Loading -> {
                    _currentWeather.value = CurrentCityWeatherDetailsState(isLoading = true)
                }
                is NetworkResult.Error -> {
                    _currentWeather.value =
                        CurrentCityWeatherDetailsState(error = it.message.toString())
                }
                is NetworkResult.Success -> {
                    _currentWeather.value = CurrentCityWeatherDetailsState(data = it.data)
                }
            }
        }.launchIn(viewModelScope)
    }
}