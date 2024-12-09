package com.example.weather

import androidx.activity.compose.setContent
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.weather.presentation.MainActivity
import com.example.weather.presentation.screens.SearchView
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MyComposeUITest {

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setup() {
        composeRule.activity.setContent {
            SearchView()
        }
    }

    @Test
    fun verifyIfAllViewsExists() {
        composeRule.apply {
            onNodeWithTag("search_text_filed").assertExists()
            onNodeWithTag("search_image").assertExists()
        }
    }

    @Test
    fun verifyIfSearchImageIsClickable() {
        composeRule.apply {
            onNodeWithTag("search_text_filed").performTextInput("Pune")
            onNodeWithTag("search_image").assertIsEnabled()
        }
    }
}