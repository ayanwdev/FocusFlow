package dev.ayanw.focusflow.utils.presetManager

import dev.ayanw.focusflow.ui.theme.TailWind
import dev.ayanw.focusflow.utils.MINUTES_MS
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


fun initDefaultPresets(presetDb: PresetDb) {
    val dao = presetDb.presetDao()

    val defaultPreset = TimerPreset(
        name = "Pomodoro",
        color = TailWind.Red_400,
        segments = listOf(
            Segment(
                type = SegmentType.FOCUS,
                duration = 25 * MINUTES_MS
            ),
            Segment(
                type = SegmentType.BREAK,
                duration = 5 * MINUTES_MS
            )
        )
    )

    runBlocking {
        launch {
            if (dao.getSize() == 0) dao.insert(defaultPreset)
        }
    }
}

