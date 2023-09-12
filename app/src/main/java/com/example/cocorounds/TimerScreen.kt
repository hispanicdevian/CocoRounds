package com.example.cocorounds

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.cocorounds.ui.theme.CoolBlack
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun TimerScreen(navController: NavHostController, viewModel: CocoRoundsViewModel) {
    val roundInputData by viewModel.roundInputData.collectAsState()
    val numberOfRounds by viewModel.numberOfRounds.collectAsState()
    val restInputData by viewModel.restInputData.collectAsState()

    var currentRound by remember { mutableIntStateOf(1) }
    var totalSeconds by remember { mutableIntStateOf(0) }
    var totalRestSeconds by remember { mutableIntStateOf(0) }
    var countdownJob: Job? by remember { mutableStateOf(null) }
    var showRestRoundScreen by remember { mutableStateOf(false) }

    LaunchedEffect(currentRound) {
        totalSeconds = (roundInputData.minutes * 60) + roundInputData.seconds

        countdownJob?.cancel()
        countdownJob = viewModel.viewModelScope.launch {
            while (totalSeconds > 0) {
                delay(1000)
                totalSeconds -= 1
            }
            //This delay makes the 0 look natural before transition to the next round
            delay(1000)

            showRestRoundScreen = true

            // Calculate the total delay duration based on user input
            totalRestSeconds = (restInputData.minutes * 60) + restInputData.seconds

            // Add the extra delay based on user input
            delay(totalRestSeconds * 1000L) // Convert to milliseconds

            //This delay makes the 0 look natural before transition to the next round
            delay(1000)

            showRestRoundScreen = false

            if (currentRound < numberOfRounds) {
                currentRound++
            } else {
                navController.popBackStack("main_screen", inclusive = false)
            }
        }
    }

    // Handle the back event (Esc, Return, Back, etc.)
    BackHandler {
        navController.navigate("main_screen") {
            popUpTo("main_screen") { inclusive = true }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        // Display the RestRoundScreen when showRestRoundScreen is true
        if (showRestRoundScreen) {
            RestRoundScreen(totalRestSeconds)
        } else {
            // Calculate minutes and seconds
            val minutesRemaining = totalSeconds / 60
            val secondsRemaining = totalSeconds % 60

            // Show the countdown information
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Round $currentRound - $numberOfRounds",
                    style = MaterialTheme.typography.displayMedium,
                    fontWeight = FontWeight.Bold,
                    color = CoolBlack
                    //modifier = Modifier.padding(bottom = 50.dp)
                )
                Text(
                    text = "$minutesRemaining : $secondsRemaining",
                    style = MaterialTheme.typography.displayLarge,
                    fontWeight = FontWeight.Bold,
                    color = CoolBlack,
                    modifier = Modifier.padding(top = 55.dp)
                )
            }
        }
    }
}