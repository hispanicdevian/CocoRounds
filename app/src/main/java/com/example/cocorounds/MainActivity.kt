package com.example.cocorounds

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.navigation.NavHost


class MainActivity : ComponentActivity() {
    private val viewModel: CocoRoundsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MainActivityContent(viewModel)
        }
    }
}

@Composable
fun MainActivityContent(viewModel: CocoRoundsViewModel) {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "main_screen") {
        composable("main_screen") {
            MainScreen(navController, viewModel)
        }
        composable("five_second_screen") { // Add the new destination
            FiveSecondScreen(navController)
        }
        composable("timer_screen") {
            CocoRoundsScreen(navController, viewModel)
        }
    }
}