package dev.ayanw.focusflow.pages

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.ayanw.focusflow.ui.components.Timer
import dev.ayanw.focusflow.utils.timerControl.SECONDS_MS
import dev.ayanw.focusflow.utils.timerControl.TimerMode


@Composable
fun Home() {
    Box(
//        contentAlignment = Alignment.Center,
        modifier =
            Modifier
                .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .padding(top = 80.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Timer(
                time = 10 * SECONDS_MS,
                mode = TimerMode.COUNTDOWN,
            )
        }
    }
}
