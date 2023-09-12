package com.example.cocorounds

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cocorounds.ui.theme.CocoRoundsTheme

class MainActivity : ComponentActivity() {
    private val viewModel: CocoRoundsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CocoRoundsTheme {

                MainActivityContent(viewModel)
            }
        }
    }
}

@Composable
fun MainActivityContent(viewModel: CocoRoundsViewModel) {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "coco_rounds_screen") {
        composable("main_screen") {
            CocoRoundsScreen(navController, viewModel)
        }
        composable("five_second_screen") { // Add the new destination
            FiveSecondScreen(navController)
        }
        composable("timer_screen") {
            TimerScreen(navController, viewModel)
        }
    }
}