package dev.ayanw.focusflow.utils

import dev.ayanw.focusflow.models.TimeComponents


class TimerManager() {

    var time = 0L
        private set

    val getTimeRaw get() = time

    val getTimeComponents
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
}
