package com.example.cocorounds.views.subscreen

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.cocorounds.ui.theme.Purple40
import kotlinx.coroutines.delay

@Composable
fun FiveSecondTimer(
    durationSeconds: Int,
    onFinish: () -> Unit
) {

    var secondsRemaining by remember(durationSeconds) { mutableIntStateOf(durationSeconds) }

    LaunchedEffect(secondsRemaining) {
        while (secondsRemaining > 1) { //This can determine the last second shown
            delay(1000)
            secondsRemaining -= 1
        }

        //This delay makes the 1 look natural before transition to the next round
        delay(800)
        onFinish()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Starting in:",
            style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.Bold),
            color = Purple40, // Custom text color
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "$secondsRemaining",
            style = MaterialTheme.typography.displayLarge.copy(fontWeight = FontWeight.Bold),
            color = Purple40, // Custom text color
            modifier = Modifier
                .padding(16.dp)
                .animateContentSize() // Animate text size changes
        )
    }
}