package com.example.cocorounds

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.cocorounds.ui.theme.Purple40

@Composable
fun CocoRoundsLandscape(navController: NavHostController, viewModel: CocoRoundsViewModel) {
    val roundInputData by viewModel.roundInputData.collectAsState()
    val restInputData by viewModel.restInputData.collectAsState()

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
        ) {

            Box(
                modifier = Modifier
                    .padding(horizontal = 100.dp)
                    .padding(top = 12.dp)
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {

                    val numberOfRoundsValue by viewModel.numberOfRounds.collectAsState()

                    RoundInput(
                        label = "Number of Rounds",
                        value = numberOfRoundsValue,
                        onValueChange = { number ->
                            viewModel.updateNumberOfRounds(number)
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                thickness = 2.dp,
                color = Purple40
            )

            // Round and Rest Input Area Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f) // Take up available space
            ) {
                // Left side (Round Input Area)
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f), // Take up half of the available space
                ) {

                    //Text("Round Time", style = MaterialTheme.typography.titleLarge)

                    RoundInput(
                        label = "Round Minutes",
                        value = roundInputData.minutes,
                        onValueChange = { minutes ->
                            viewModel.updateRoundInput(minutes, roundInputData.seconds)
                        }
                    )

                    RoundInput(
                        label = "Round Seconds",
                        value = roundInputData.seconds,
                        onValueChange = { seconds ->
                            viewModel.updateRoundInput(roundInputData.minutes, seconds)
                        }
                    )
                }
                Divider(
                    modifier = Modifier
                        .width(width = 2.dp)
                        .height(175.dp)
                        .align(alignment = CenterVertically)
                        .padding(bottom = 16.dp),
                    color = Purple40
                )

                // Right side (Rest Input Area)
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f), // Take up half of the available space
                ) {
                    // Rest Input Area

                    //Text("Rest Time", style = MaterialTheme.typography.titleLarge)

                    RestInput(
                        label = "Rest Minutes",
                        value = restInputData.minutes,
                        onValueChange = { minutes ->
                            viewModel.updateRestInput(minutes, restInputData.seconds)
                        }
                    )

                    RestInput(
                        label = "Rest Seconds",
                        value = restInputData.seconds,
                        onValueChange = { seconds ->
                            viewModel.updateRestInput(restInputData.minutes, seconds)
                        }
                    )
                }
            }

            // Ready and Clear Buttons at the bottom
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
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
