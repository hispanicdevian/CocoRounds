package com.example.cocorounds

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.navigation.NavHostController

@Composable
fun CocoRoundsScreen(navController: NavHostController, viewModel: CocoRoundsViewModel) {
    val configuration = LocalConfiguration.current
    val isPortrait = configuration.orientation == Configuration.ORIENTATION_PORTRAIT

    if (isPortrait) {
        CocoRoundsPortrait(viewModel)
    } else {

        CocoRoundsLandscape(viewModel)
    }
}