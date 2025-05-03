package dev.ayanw.focusflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.ayanw.focusflow.ui.theme.FocusFlowTheme
import kotlinx.coroutines.delay

enum class TimerMode {
    STOPWATCH,
    COUNTDOWN
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
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        Column (
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            MainTimer(
                                modifier = Modifier.padding(innerPadding),
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

@Preview
@Composable
fun MainButton(
        modifier: Modifier = Modifier,
        isRunning: Boolean = false,
        onClick: () -> Unit = {}
) {
    Button(
        onClick = onClick,
        modifier = modifier
    ) {
        Text(
            text = if (isRunning) "Pause" else "Start",
            style = TextStyle(
                fontSize = 18.sp,
            )
        )
    }
}
//
//@Preview(showBackground = true)
//@Composable
//fun TimerTag() {
//    Row (
//        verticalAlignment = Alignment.CenterVertically,
//        horizontalArrangement = Arrangement.Center,
//        modifier = Modifier
//            .padding(all = 8.dp)
//            .border(
//                width = 1.dp,
//                color = MaterialTheme.colorScheme.primary,
//                shape = MaterialTheme.shapes.small
//            )
//    ) {
//        Box (modifier = Modifier
//                .background(color = MaterialTheme.colorScheme.primary)
//                .padding(all = 16.dp)
//        )
//        Text(
//            text = "Focus",
//            style = TextStyle(
//                fontSize = 18.sp,
//            )
//        )
//    }
//}



@Preview(showBackground = true)
@Composable
fun MainTimer(
        modifier: Modifier = Modifier,
        mode: TimerMode = TimerMode.COUNTDOWN,
        time: Int = 1500 // 25 minutes
    ) {
    var timeLeft by remember { mutableStateOf(time) }
    var isRunning by remember { mutableStateOf(false) }

    LaunchedEffect(isRunning) {
        while (isRunning && timeLeft > 0) {
            delay(1000L)
            timeLeft--
        }
    }

    Column (
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = formatTime(timeLeft),
            style = TextStyle(
                fontSize = 52.sp,
            ),
            modifier = modifier
        )
        MainButton(
            modifier = Modifier.padding(all = 8.dp),
            isRunning = isRunning,
            onClick = {
                isRunning = !isRunning
            }
        )
    }
}