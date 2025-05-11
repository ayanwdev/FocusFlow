package dev.ayanw.focusflow.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material.icons.sharp.Close
import androidx.compose.material.icons.sharp.Pause
import androidx.compose.material.icons.sharp.Refresh
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.ayanw.focusflow.ui.components.buttons.PresetSelectionButton
import dev.ayanw.focusflow.ui.components.buttons.TimeControlButton
import dev.ayanw.focusflow.ui.theme.Roboto_Mono
import dev.ayanw.focusflow.ui.theme.TailWind
import dev.ayanw.focusflow.utils.TimerControl
import dev.ayanw.focusflow.utils.TimerMode
import dev.ayanw.focusflow.utils.TimerState


@Preview(showBackground = true)
@Composable
fun Timer(
    modifier: Modifier = Modifier,
    mode: TimerMode = TimerMode.COUNTDOWN,
    time: Int = 25 * 60 * 1000,
) {
    var remaining by remember {
        mutableStateOf(if (mode == TimerMode.COUNTDOWN) time else 0)
    }
    var state by remember { mutableStateOf(TimerState.STOPPED) }
    val coroutineScope = rememberCoroutineScope()

    val timerControl = remember {
        TimerControl(
            mode = TimerMode.COUNTDOWN,
            initialTime = time,
            onTick = { newTime -> remaining = newTime },
            onFinish = { state = TimerState.FINISHED }
        )
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        PresetSelectionButton()

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .size(300.dp)
                .aspectRatio(1f)
                .background(Color.Transparent)
                .border(
                    width = 20.dp,
                    brush = Brush.linearGradient(
                        colors = listOf(
                            TailWind.Indigo_500,
                            TailWind.Violet_500
                        )
                    ),
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .size(260.dp)
                    .border(
                        width = 5.dp,
                        color = TailWind.Emerald_500,
                        shape = CircleShape
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                val formattedTime = timerControl.formatTime(remaining)
                Text(
                    text =
                        "${formattedTime.hours}:${formattedTime.minutes}:${formattedTime.seconds}",
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
                TimeControlButton(
                    size = 70.dp,
                    iconSize = 60.dp,
                    icon = when (state) {
                        TimerState.RUNNING -> Icons.Sharp.Pause
                        TimerState.PAUSED -> Icons.Outlined.PlayArrow
                        TimerState.STOPPED -> Icons.Outlined.PlayArrow
                        TimerState.FINISHED -> Icons.Sharp.Refresh
                    },
                    onClick = {
                        when (state) {
                            TimerState.RUNNING -> {
                                timerControl.pause()
                                state = TimerState.PAUSED
                            }

                            TimerState.PAUSED, TimerState.STOPPED -> {
                                timerControl.start(coroutineScope)
                                state = TimerState.RUNNING
                            }

                            TimerState.FINISHED -> {
                                timerControl.reset()
                                remaining = time
                                state = TimerState.STOPPED
                            }
                        }
                    },
                )
            }
            TimeControlButton(
                enabled = state == TimerState.PAUSED,
                onClick = {
                    timerControl.reset()
                    remaining = if (mode == TimerMode.COUNTDOWN) time else 0
                    state = TimerState.STOPPED
                },
                bgColor = TailWind.Pink_500,
                icon = Icons.Sharp.Close,
                modifier = modifier.offset(
                    x = 100.dp,
                    y = (-84).dp
                )
            )
        }
    }
}