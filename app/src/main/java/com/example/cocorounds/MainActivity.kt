package com.example.cocorounds

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cocorounds.viewmodel.CocoRoundsViewModel
import com.example.cocorounds.views.CocoRoundsScreen
import com.example.cocorounds.views.FiveSecondScreen
import com.example.cocorounds.views.TimerScreen

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

    NavHost(navController, startDestination = "coco_rounds_screen") {
        composable("coco_rounds_screen") { // Use the correct destination name
            CocoRoundsScreen(navController = navController, viewModel = viewModel)
        }

        composable("five_second_screen") { // Add the new destination
            FiveSecondScreen(navController)
        }

        composable("timer_screen") {
            TimerScreen(navController = navController, viewModel = viewModel)
        }
    }
}

