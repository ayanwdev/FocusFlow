package dev.ayanw.focusflow.utils.timerManager

import dev.ayanw.focusflow.models.TimeComponents


class Timer() {

    private var time = 0L
    var state = TimerState.STOPPED

    val getTimeRaw get() = time

    val getTimeComponents: () -> TimeComponents
        get() = {
            val hours = (time / 3600000).toInt()
            val minutes = ((time % 3600000) / 60000).toInt()
            val seconds = ((time % 60000) / 1000).toInt()

            TimeComponents(
                hours = hours,
                minutes = minutes,
                seconds = seconds
            )
        }

    val setTime: (Long) -> Unit = { time = it }

    fun start() {
        state = TimerState.RUNNING
    }

    fun pause() {
        state = TimerState.PAUSED
    }

    fun stop() {
        state = TimerState.STOPPED
    }

    fun reset() {
        state = TimerState.FINISHED
    }

}
