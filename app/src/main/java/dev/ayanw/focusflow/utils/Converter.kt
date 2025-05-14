package dev.ayanw.focusflow.utils

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dev.ayanw.focusflow.utils.presetManager.Segment

class Converter {

    @TypeConverter
    fun fromColor(color: Color): Int = color.toArgb()

    @TypeConverter
    fun toColor(argb: Int): Color = Color(argb)

    @TypeConverter
    fun fromSegmentList(segments: List<Segment>): String = Gson().toJson(segments)

    @TypeConverter
    fun toSegmentList(json: String): List<Segment> {
        val type = object : TypeToken<List<Segment>>() {}.type
        return Gson().fromJson(json, type)
    }
}
