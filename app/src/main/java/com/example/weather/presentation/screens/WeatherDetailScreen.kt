package com.example.weather.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.weather.data.models.CityWeather
import com.example.weather.presentation.viewmodels.WeatherViewModel


@Composable
fun WeatherDetailScreen() {
    val categoryViewModel: WeatherViewModel = viewModel()
    val cityWeatherDetailsState = categoryViewModel.currentWeather.value


    if (cityWeatherDetailsState.isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }

    if (cityWeatherDetailsState.error.isNotBlank()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = "Failed to fetch data: ${cityWeatherDetailsState.error.toString()}",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace,
                modifier = Modifier
                    .padding(16.dp)
            )
        }
    }

    if (cityWeatherDetailsState.data != null) {
        Box(
            modifier = Modifier.fillMaxSize(1f),
            contentAlignment = Alignment.Center
        ) {
            weatherUi(cityWeatherDetailsState.data)
        }
    }

}

@Composable
fun weatherUi(weatherData: CityWeather?) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround,
        modifier = Modifier.padding(10.dp)
    ) {
        Image(
            painter = rememberAsyncImagePainter("${weatherData?.current?.weatherIcons?.get(0)}"),
            contentScale = ContentScale.FillHeight,
            contentDescription = "weather icon",
            modifier = Modifier
                .padding(end = 8.dp)
                .weight(0.6f)
        )

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(0.3f)
        ) {
            Text(
                text = weatherData?.current?.temperature.toString() + "\u2103",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.h3, fontFamily = FontFamily.SansSerif
            )
            weatherData?.current?.weatherDescriptions?.let {
                Text(
                    text = it[0],
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.h4, fontFamily = FontFamily.SansSerif
                )
            }

        }

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.2f)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = com.example.weather.R.drawable.ic_humidity),
                    contentDescription = "Camera Icon",
                    modifier = Modifier.size(30.dp)

                )
                Spacer(modifier = Modifier.padding(4.dp))
                Column() {
                    Text(
                        text = "${weatherData?.current?.humidity.toString()}%",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold, fontFamily = FontFamily.SansSerif
                    )
                    Text(text = "Humidity", color = Color.Black, fontFamily = FontFamily.SansSerif)
                }
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = com.example.weather.R.drawable.ic_wind_speed),
                    contentDescription = "Camera Icon",
                    modifier = Modifier.size(30.dp)

                )
                Spacer(modifier = Modifier.padding(4.dp))
                Column() {
                    Text(
                        text = "${weatherData?.current?.windSpeed.toString()}/KmH",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold, fontFamily = FontFamily.SansSerif
                    )
                    Text(
                        text = "Wind Speed",
                        color = Color.Black,
                        fontFamily = FontFamily.SansSerif
                    )
                }
            }
        }

        Text(
            text = "Current Weather  :  ${weatherData?.request?.query}",
            color = Color.Black,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold
        )
    }
}