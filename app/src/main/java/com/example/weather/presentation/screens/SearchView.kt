package com.example.weather.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weather.R
import com.example.weather.presentation.viewmodels.WeatherViewModel
import com.example.weather.ui.theme.LightBlue

@Composable
fun SearchView() {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(5.dp)
            .testTag("search_view")
    ) {
        val weatherViewModel: WeatherViewModel = viewModel()
        val state = remember {
            mutableStateOf("")
        }
        TextField(
            shape = RoundedCornerShape(10.dp),
            colors = TextFieldDefaults.textFieldColors(
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent
            ),
            value = state.value,
            onValueChange = {
                state.value = it
            },
            label = {
                Text(
                    stringResource(id = R.string.text_field_label),
                    fontFamily = FontFamily.SansSerif
                )
            },
            modifier = Modifier
                .background(LightBlue)
                .weight(0.8f)
                .testTag("search_text_filed")
        )

        Image(
            painter = painterResource(id = R.drawable.ic_search),
            contentDescription = "search image",
            modifier = Modifier
                .size(30.dp)
                .weight(0.2f)
                .testTag("search_image")
                .clickable(state.value.isNotEmpty()) { weatherViewModel.getCityWeatherDetails(state.value) }
        )
    }
}