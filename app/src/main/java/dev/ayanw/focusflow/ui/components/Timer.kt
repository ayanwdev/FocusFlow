package dev.ayanw.focusflow.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.ayanw.focusflow.utils.TimerManager
import java.util.Locale
import java.util.concurrent.TimeUnit

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Timer() {
    // Hold TimerManager and time state
    val timer = remember { TimerManager() }
    var time by remember { mutableStateOf(timer.getTimeComponents()) }
    var bottomSheetState by rememberSaveable { mutableStateOf(true) }

    // Picker state, initialized from current timer value
    var hours by rememberSaveable { mutableIntStateOf(time.hours) }
    var minutes by rememberSaveable { mutableIntStateOf(time.minutes) }
    var seconds by rememberSaveable { mutableIntStateOf(time.seconds) }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.clickable {

                hours = time.hours
                minutes = time.minutes
                seconds = time.seconds

                bottomSheetState = true
            }
        ) {

            listOf(
                time.hours,
                time.minutes,
                time.seconds
            ).forEachIndexed { i, unit ->
                Text(
                    text = String.format(Locale.getDefault(), "%02d", unit),
                    fontSize = 42.sp,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
                if (i < 2) {
                    Text(
                        text = ":",
                        fontSize = 35.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(horizontal = 8.dp)
                    )
                }
            }

        }
    }

    if (bottomSheetState) {
        ModalBottomSheet(
            onDismissRequest = { bottomSheetState = false }
        ) {
            Column {
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    NumberPickerView(
                        value = hours,
                        range = 0..23,
                        onValueChange = { hours = it }
                    )
                    Text(
                        text = ":",
                        fontSize = 35.sp,
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(horizontal = 8.dp)
                    )
                    NumberPickerView(
                        value = minutes,
                        range = 0..59,
                        onValueChange = { minutes = it }
                    )
                    Text(
                        text = ":",
                        fontSize = 35.sp,
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(horizontal = 8.dp)
                    )
                    NumberPickerView(
                        value = seconds,
                        range = 0..59,
                        onValueChange = { seconds = it }
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
//                    Text("${timer.getTimeComponents()}")
                    Button(
                        onClick = {
                            val totalMillis = TimeUnit.HOURS.toMillis(hours.toLong()) +
                                    TimeUnit.MINUTES.toMillis(minutes.toLong()) +
                                    TimeUnit.SECONDS.toMillis(seconds.toLong())
                            timer.setTime(totalMillis)
                            time =
                                timer.getTimeComponents() // Update state to trigger recomposition
                            bottomSheetState = false
                        }
                    ) {
                        Text(text = "Save")
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}