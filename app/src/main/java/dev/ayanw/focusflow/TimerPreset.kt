package dev.ayanw.focusflow

enum class SegmentType {
    WORK,
    BREAK
}

data class TimerSegment(
    val type: SegmentType,
    val duration: Int = 1500 // in seconds
)

data class TimerPreset(
    val name: String = "New Preset",
    val segments: List<TimerSegment>,
    val isDefault: Boolean = false
)