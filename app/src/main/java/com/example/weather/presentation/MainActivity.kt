package com.example.weather.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.weather.R
import com.example.weather.presentation.screens.SearchView
import com.example.weather.presentation.screens.WeatherDetailScreen
import com.example.weather.ui.theme.WeatherTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            WeatherTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(
                                    stringResource(id = R.string.toolbar_title),
                                    textAlign = TextAlign.Center
                                )
                            },
                            modifier = Modifier.background(Color.White)
                        )
                    },
                    content = { paddingValues ->
                        Box(
                            modifier = Modifier
                                .padding(paddingValues)
                                .fillMaxWidth()
                        ) {
                            WeatherDetailScreen()
                        }
                    },
                    bottomBar = {
                        BottomAppBar(
                            backgroundColor = Color.White,
                            modifier = Modifier.size(width = Dp.Infinity, height = 70.dp)
                        ) {
                            SearchView()
                        }
                    }
                )
            }
        }
    }
}