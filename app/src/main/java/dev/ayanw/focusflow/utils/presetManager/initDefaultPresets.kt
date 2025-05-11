package dev.ayanw.focusflow.utils.presetManager

import dev.ayanw.focusflow.ui.theme.TailWind
import dev.ayanw.focusflow.utils.MINUTES_MS
import kotlinx.coroutines.runBlocking


fun initDefaultPresets(dao: PresetDao) {
    val defaultPresets = listOf(
        TimerPreset(
            id = 0,
            name = "Pomodoro",
            color = TailWind.Red_400,
            segments = listOf(
                Segment(
                    type = SegmentType.FOCUS,
                    duration = 25 * MINUTES_MS
                ),
                Segment(
                    type = SegmentType.BREAK,
                    duration = 5 * MINUTES_MS,
                )
            )
        )
    )

    runBlocking {
        defaultPresets.forEach { preset ->
            val existingPreset = dao.getPresetById(preset.id)
            existingPreset.collect { presetFromDb ->
                dao.upsertPreset(preset)
            }
        }
    }
}