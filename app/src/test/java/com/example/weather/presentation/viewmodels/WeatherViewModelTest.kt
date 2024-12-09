import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.weather.common.NetworkResult
import com.example.weather.data.models.CityWeather
import com.example.weather.data.models.Request
import com.example.weather.domain.use_cases.GetCityCurrentWeatherUseCase
import com.example.weather.presentation.viewmodels.WeatherViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class WeatherViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    // TestCoroutineDispatcher to manage the test's coroutine dispatcher
    private val testDispatcher = StandardTestDispatcher()

    // Instance of the ViewModel we are testing
    private lateinit var weatherViewModel: WeatherViewModel

    @Mock
    private lateinit var getCityCurrentWeatherUseCase: GetCityCurrentWeatherUseCase

    @Before
    fun setUp() {
        // Initialize Mockito mocks
        MockitoAnnotations.initMocks(this)
        kotlinx.coroutines.Dispatchers.setMain(testDispatcher)
        // Create the ViewModel instance
        weatherViewModel = WeatherViewModel(getCityCurrentWeatherUseCase)
    }

    @Test
    fun `getCityWeatherDetails should update state with Success when the use case returns Success`() =
        runBlocking {
            // Given
            val cityName = "New York"
            val weatherData = CityWeather(
                request = Request(type = "success")
            )
            val successFlow = flow {
                emit(NetworkResult.Success(weatherData))
            }

            // When the use case returns Success
            Mockito.`when`(getCityCurrentWeatherUseCase(cityName)).thenReturn(successFlow)

            // Act
            weatherViewModel.getCityWeatherDetails(cityName)
            testDispatcher.scheduler.advanceUntilIdle()
            // Assert
            assertEquals("success", weatherViewModel.currentWeather.value.data?.request?.type)
        }

    @Test
    fun `getCityWeatherDetails should update state with Error when the use case returns Error`() =
        runBlocking {
            // Given
            val cityName = "New York"
            val weatherData = CityWeather()
            val errorFlow = flow {
                emit(NetworkResult.Error("Something went wrong",weatherData))
            }
            // When the use case returns Error
            Mockito.`when`(getCityCurrentWeatherUseCase(cityName)).thenReturn(errorFlow)
            // Act
            weatherViewModel.getCityWeatherDetails(cityName)
            testDispatcher.scheduler.advanceUntilIdle()
            // Assert
            assertEquals("Something went wrong", weatherViewModel.currentWeather.value.error)
        }
}
