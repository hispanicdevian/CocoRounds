package com.example.cocorounds

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@Composable
fun FiveSecondScreen(navController: NavHostController) { // Include the NavHostController

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        FiveSecondTimer(
            durationSeconds = 5,
        ) {
            navController.navigate("timer_screen")
        }
    }
}