package dev.ayanw.focusflow.utils.presetManager

import dev.ayanw.focusflow.utils.MINUTES_MS

data class Segment(
    val type: SegmentType,
    val duration: Int = 5 * MINUTES_MS
)