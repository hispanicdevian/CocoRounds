package com.example.cocorounds

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.cocorounds.ui.theme.Purple40

@Composable
fun CocoRoundsPortrait(navController: NavHostController, viewModel: CocoRoundsViewModel) {
    val roundInputData by viewModel.roundInputData.collectAsState()
    val restInputData by viewModel.restInputData.collectAsState()

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
        ) {

            Spacer(modifier = Modifier.height(32.dp))

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxWidth()
            ) {

                Text("Round Timer", style = MaterialTheme.typography.headlineLarge)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Separate "Rounds" into its own row
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text("Rounds", style = MaterialTheme.typography.headlineSmall)

                Spacer(modifier = Modifier.width(8.dp))

                val numberOfRoundsValue by viewModel.numberOfRounds.collectAsState()

                RoundInput(
                    label = "Number",
                    value = numberOfRoundsValue,
                    onValueChange = { number ->
                        viewModel.updateNumberOfRounds(number) // Update the view model
                    }
                )
            }

            RoundInput(
                label = "Minutes",
                value = roundInputData.minutes,
                onValueChange = { minutes ->
                    viewModel.updateRoundInput(minutes, roundInputData.seconds)
                }
            )

            RoundInput(
                label = "Seconds",
                value = roundInputData.seconds,
                onValueChange = { seconds ->
                    viewModel.updateRoundInput(roundInputData.minutes, seconds)
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Divider(
                modifier = Modifier.fillMaxWidth(),
                thickness = 2.dp,
                color = Purple40
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Rest Input Area
            Text("Rest Time", style = MaterialTheme.typography.headlineSmall)

            RestInput(
                label = "Minutes",
                value = restInputData.minutes,
                onValueChange = { minutes ->
                    viewModel.updateRestInput(minutes, restInputData.seconds)
                }
            )

            RestInput(
                label = "Seconds",
                value = restInputData.seconds,
                onValueChange = { seconds ->
                    viewModel.updateRestInput(restInputData.minutes, seconds)
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Ready and Clear Buttons
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {

                Row {
                    ReadyClearButton(
                        readyButtonText = "Ready",
                        clearButtonText = "Clear",
                        onReadyClick = { viewModel.onReadyButtonClick(navController) },
                        onClearClick = { viewModel.onClearButtonClick() }
                    )
                }
            }
        }
    }
}