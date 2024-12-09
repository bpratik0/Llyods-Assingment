package com.example.weather.domain.use_cases

import com.example.weather.common.NetworkResult
import com.example.weather.data.models.CityWeather
import com.example.weather.data.models.Request
import com.example.weather.domain.repository.CityWeatherRepo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetCityCurrentWeatherUseCaseTest {

    private lateinit var getCityCurrentWeatherUseCase: GetCityCurrentWeatherUseCase
    private lateinit var cityWeatherRepo: CityWeatherRepo

    @Before
    fun setup() {
        cityWeatherRepo = mock(CityWeatherRepo::class.java)
        getCityCurrentWeatherUseCase = GetCityCurrentWeatherUseCase(cityWeatherRepo)
    }

    @Test
    fun `test invoke success scenario`() = runTest {
        val cityName = "Mumbai"
        val mockCityWeather = CityWeather(request = Request(type = "success"))
        `when`(cityWeatherRepo.getCityWeather(cityName)).thenReturn(mockCityWeather)
        val emissions = mutableListOf<NetworkResult<CityWeather>>()
        val flow = getCityCurrentWeatherUseCase(cityName)

        // Collect flow emissions
        flow.collect { emissions.add(it) }


        // Assertion
        assertTrue(emissions[0] is NetworkResult.Loading)
        assertTrue(emissions[1] is NetworkResult.Success)
        assertEquals(mockCityWeather, (emissions[1] as NetworkResult.Success).data)
    }

    @Test
    fun `test invoke error scenario`() = runTest {
        val cityName = "Mumbai"
        val mockCityWeather =
            CityWeather(request = Request(type = null))
        `when`(cityWeatherRepo.getCityWeather(cityName)).thenReturn(mockCityWeather)
        val emissions = mutableListOf<NetworkResult<CityWeather>>()
        val flow = getCityCurrentWeatherUseCase(cityName)

        // Collect flow emissions
        flow.collect { emissions.add(it) }

        // Assertion
        assertTrue(emissions[0] is NetworkResult.Loading)
        assertTrue(emissions[1] is NetworkResult.Error)
        assertEquals("Something Went Wrong", (emissions[1] as NetworkResult.Error).message)
    }

    @Test
    fun `test invoke emits loading state first`() = runTest {
        val cityName = "Mumbai"
        val mockCityWeather = CityWeather(request = Request(type = "success"))
        `when`(cityWeatherRepo.getCityWeather(cityName)).thenReturn(mockCityWeather)

        val emissions = mutableListOf<NetworkResult<CityWeather>>()
        val flow = getCityCurrentWeatherUseCase(cityName)

        // Collect flow emissions
        flow.collect { emissions.add(it) }

        // Assertion
        assertTrue(emissions[0] is NetworkResult.Loading)
        assertTrue(emissions[1] is NetworkResult.Success)
        assertEquals(mockCityWeather, (emissions[1] as NetworkResult.Success).data)
    }

}