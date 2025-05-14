package dev.ayanw.focusflow.utils.presetManager

import androidx.compose.ui.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TimerPreset(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val color: Color,
    val segments: List<Segment>
)
