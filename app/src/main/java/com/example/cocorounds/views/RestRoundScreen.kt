package com.example.cocorounds.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.cocorounds.views.subscreen.RestRoundTimer

@Composable
fun RestRoundScreen(totalRestSeconds: Int) {

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxWidth()
    ) {

        RestRoundTimer(remainingRest = totalRestSeconds)
    }
}