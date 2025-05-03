package dev.ayanw.focusflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.ayanw.focusflow.ui.theme.FocusFlowTheme
import kotlinx.coroutines.delay

enum class TimerMode {
    STOPWATCH,
    COUNTDOWN,
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FocusFlowTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier =
                            Modifier
                                .fillMaxSize(),
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            MainTimer(
                                modifier = Modifier.padding(innerPadding),
                                time = 5,
                                mode = TimerMode.STOPWATCH,
                            )
                        }
                    }
                }
            }
        }
    }
}

fun formatTime(seconds: Int): String {
    val hours = seconds / 3600
    val minutes = (seconds % 3600) / 60
    val remainingSeconds = seconds % 60

    return "%02d:%02d:%02d".format(hours, minutes, remainingSeconds)
}

@Composable
fun timeControlButton(
    modifier: Modifier = Modifier,
    size: Dp = 50.dp,
    iconSize: Dp = 40.dp,
    icon: ImageVector = Icons.Rounded.Refresh,
    onClick: () -> Unit = {},
) {
    Button(
        modifier =
            modifier
                .height(size)
                .width(size),
        onClick = onClick,
        shape = RoundedCornerShape(50),
        contentPadding = PaddingValues(0.dp),
    ) {
        Icon(
            modifier =
                Modifier
                    .height(iconSize)
                    .width(iconSize),
            imageVector = icon,
            contentDescription = "Reset",
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainTimer(
    modifier: Modifier = Modifier,
    mode: TimerMode = TimerMode.COUNTDOWN,
    time: Int = 1500,
) {
    var timeLeft by remember(mode) {
        mutableStateOf(if (mode == TimerMode.COUNTDOWN) time else 0)
    }
    var isRunning by remember { mutableStateOf(false) }

    if (timeLeft == 0 && mode == TimerMode.COUNTDOWN) isRunning = false

    LaunchedEffect(isRunning, mode) {
        while (isRunning) {
            delay(1000L)
            if (mode == TimerMode.COUNTDOWN && timeLeft > 0) {
                timeLeft--
            } else if (mode == TimerMode.STOPWATCH) {
                timeLeft++
            }
        }
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = formatTime(timeLeft),
            style = TextStyle(fontSize = 52.sp),
            modifier = modifier.clickable {},
        )
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            timeControlButton(
                onClick = {
                    timeLeft = if (mode == TimerMode.COUNTDOWN) time else 0
                    isRunning = false
                },
            )
            timeControlButton(
                icon = if (isRunning) Icons.Rounded.Close else Icons.Rounded.PlayArrow,
                onClick = { isRunning = !isRunning },
            )
        }
    }
}
