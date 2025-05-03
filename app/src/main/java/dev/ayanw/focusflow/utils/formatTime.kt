package dev.ayanw.focusflow.utils

fun formatTime(seconds: Int): String {
    val hours = seconds / 3600
    val minutes = (seconds % 3600) / 60
    val remainingSeconds = seconds % 60

    return "%02d:%02d:%02d".format(hours, minutes, remainingSeconds)
}
