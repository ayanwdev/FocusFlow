package dev.ayanw.focusflow.utils

import android.os.SystemClock
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

const val SECONDS_MS = 1000
const val MINUTES_MS = 60 * SECONDS_MS
const val HOURS_MS = 60 * MINUTES_MS

enum class TimerMode {
    STOPWATCH,
    COUNTDOWN,
}

enum class TimerState {
    RUNNING,
    PAUSED,
    STOPPED,
    FINISHED,
}

data class FormattedTime(
    val hours: String,
    val minutes: String,
    val seconds: String,
    val milliseconds: String
)

class TimerControl(
    private val mode: TimerMode,
    private val initialTime: Int,
    private val onTick: (Int) -> Unit,
    private val onFinish: () -> Unit
) {
    private var timerJob: Job? = null
    private var startTimestamp = 0L
    private var elapsedBeforePause = 0

    var state: TimerState = TimerState.STOPPED
        private set

    fun start(scope: CoroutineScope) {
        if (state == TimerState.RUNNING) return

        state = TimerState.RUNNING
        startTimestamp = SystemClock.elapsedRealtime()

        timerJob = scope.launch {
            while (isActive) {
                val now = SystemClock.elapsedRealtime()
                val elapsed = (now - startTimestamp).toInt() + elapsedBeforePause

                val newTime = when (mode) {
                    TimerMode.STOPWATCH -> elapsed
                    TimerMode.COUNTDOWN -> initialTime - elapsed
                }

                onTick(newTime)

                if (mode == TimerMode.COUNTDOWN && newTime <= 0) {
                    state = TimerState.FINISHED
                    onFinish()
                    break
                }

                delay(100L)
            }
        }
    }

    fun pause() {
        if (state != TimerState.RUNNING) return
        state = TimerState.PAUSED
        elapsedBeforePause += (SystemClock.elapsedRealtime() - startTimestamp).toInt()
        timerJob?.cancel()
    }

    fun reset() {
        timerJob?.cancel()
        state = TimerState.STOPPED
        elapsedBeforePause = 0
    }

    fun formatTime(ms: Int): FormattedTime {
        val hours = (ms / HOURS_MS).toString().padStart(2, '0')
        val minutes = ((ms % HOURS_MS) / MINUTES_MS).toString().padStart(2, '0')
        val seconds = ((ms % MINUTES_MS) / SECONDS_MS).toString().padStart(2, '0')
        val milliseconds = (ms % SECONDS_MS).toString().padStart(3, '0')

        return FormattedTime(hours, minutes, seconds, milliseconds)
    }
}
