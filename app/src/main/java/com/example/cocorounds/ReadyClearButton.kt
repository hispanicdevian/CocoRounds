package com.example.cocorounds

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cocorounds.ui.theme.Purple40
import com.example.cocorounds.ui.theme.PurpleGrey40

@Composable
fun ReadyClearButton(
    readyButtonText: String,
    clearButtonText: String,
    onReadyClick: () -> Unit,
    onClearClick: () -> Unit
) {

    Row(
        modifier = Modifier.fillMaxWidth()
    ) {

        Button(
            onClick = onReadyClick,
            colors = ButtonDefaults.buttonColors(Purple40),
            modifier = Modifier
                .weight(1f)
        ) {

            Text(text = readyButtonText)
        }

        Spacer(modifier = Modifier.width(8.dp))

        Button(
            onClick = onClearClick,
            colors = ButtonDefaults.buttonColors(PurpleGrey40),
            modifier = Modifier
                .weight(1f)
        ) {

            Text(text = clearButtonText)
        }
    }
}