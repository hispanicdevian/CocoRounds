package com.example.cocorounds

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import com.example.cocorounds.ui.theme.CherryOrange
import kotlinx.coroutines.delay

@Composable
fun RestRoundTimer(
    remainingRest: Int,
) {
    var totalRestRemaining by remember(remainingRest) { mutableIntStateOf(remainingRest) }

    LaunchedEffect(totalRestRemaining) {
        while (totalRestRemaining > 0) { //This can determine the last second shown
            delay(1000)
            totalRestRemaining -= 1
        }
        //This delay makes the 0 look natural before transition to the next round
        delay(1000)
    }

    // Calculate minutes and seconds
    val minutesRemaining = totalRestRemaining / 60
    val secondsRemaining = totalRestRemaining % 60

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = "Rest Round:",
                style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.Bold),
                color = CherryOrange,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = "$minutesRemaining : $secondsRemaining",
                style = MaterialTheme.typography.displayLarge.copy(fontWeight = FontWeight.Bold),
                color = CherryOrange,
                modifier = Modifier
                    .padding(top = 50.dp)
                    .animateContentSize()
            )
        }
    }
}