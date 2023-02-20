package com.e.tmdb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.e.tmdb.ui.screens.MainScreen
import com.e.tmdb.ui.theme.TMDBTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController() // main screen controller
            fun navigateToDetails(id: Int?) {
                navController.navigate("details/${id}")
            }
            TMDBTheme {
                MainScreen(
                    navController = navController,
                    navigateToDetails = ::navigateToDetails,
                )
            }
        }
    }
}
