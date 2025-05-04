package dev.ayanw.focusflow.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.ayanw.focusflow.R
import dev.ayanw.focusflow.TimerMode
import dev.ayanw.focusflow.ui.theme.Roboto_Mono
import dev.ayanw.focusflow.ui.theme.TwColors
import dev.ayanw.focusflow.utils.formatTime
import kotlinx.coroutines.delay

enum class TimerState {
    RUNNING,
    PAUSED,
    STOPPED,
    FINISHED,
}

@Preview(showBackground = true)
@Composable
fun MainTimer(
    modifier: Modifier = Modifier,
    mode: TimerMode = TimerMode.COUNTDOWN,
    time: Int = 1500,
) {
    var state: TimerState by remember { mutableStateOf(TimerState.STOPPED) }
    var remaining by remember(mode) {
        mutableStateOf(if (mode == TimerMode.COUNTDOWN) time else 0)
    }

    if (remaining == 0 && mode == TimerMode.COUNTDOWN) state = TimerState.FINISHED

    LaunchedEffect(state, mode) {
        while (state == TimerState.RUNNING) {
            delay(1000L)
            if (mode == TimerMode.COUNTDOWN && remaining > 0) {
                remaining--
            } else if (mode == TimerMode.STOPWATCH) {
                remaining++
            }
        }
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(300.dp)
                .background(
                    Color.Transparent
                )
                .border(
                    width = 20.dp,
                    brush = Brush.linearGradient(
                        colors = listOf(
                            TwColors.Indigo_500,
                            TwColors.Violet_500
                        )
                    ),
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(260.dp)
                    .background(
                        Color.Transparent
                    )
                    .border(
                        width = 5.dp,
                        color = TwColors.Emerald_500,
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = formatTime(remaining),
                    style = TextStyle(fontSize = 40.sp),
                    fontFamily = Roboto_Mono,
                )
            }
        }
        Column {
            Row(
                modifier = Modifier
                    .padding(top = 40.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                timeControlButton(
                    size = 70.dp,
                    iconSize = 60.dp,
//                    icon = if (isRunning) R.drawable.ic_pause else R.drawable.ic_play,
                    icon = when (state) {
                        TimerState.RUNNING -> R.drawable.ic_pause
                        TimerState.PAUSED -> R.drawable.ic_play
                        TimerState.STOPPED -> R.drawable.ic_play
                        TimerState.FINISHED -> R.drawable.ic_refresh
                    },
                    onClick = {
                        state = when (state) {
                            TimerState.RUNNING -> TimerState.PAUSED
                            TimerState.PAUSED -> TimerState.RUNNING
                            TimerState.STOPPED -> TimerState.RUNNING
                            TimerState.FINISHED -> {
                                remaining = time
                                TimerState.RUNNING
                            }
                        }
                    },
                )

            }
            timeControlButton(
                enabled = state == TimerState.PAUSED,
                onClick = {
                    remaining = if (mode == TimerMode.COUNTDOWN) time else 0
                    state = TimerState.STOPPED
                },
                bgColor = TwColors.Pink_500,
                icon = R.drawable.ic_stop,
                modifier = modifier.offset(
                    x = 100.dp,
                    y = (-84).dp
                )
            )
        }
    }
}
