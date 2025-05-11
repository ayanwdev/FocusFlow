package dev.ayanw.focusflow.utils.presetManager

import androidx.compose.ui.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.ayanw.focusflow.ui.theme.TailWind

@Entity
data class TimerPreset(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val color: Color = TailWind.Slate_50,
    val segments: List<Segment>
)
