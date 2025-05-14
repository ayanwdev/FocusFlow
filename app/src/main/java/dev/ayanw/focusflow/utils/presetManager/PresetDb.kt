package dev.ayanw.focusflow.utils.presetManager

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.ayanw.focusflow.utils.Converter


@Database(
    entities = [TimerPreset::class],
    version = 1,
)
@TypeConverters(Converter::class)
abstract class PresetDb : RoomDatabase() {
    abstract fun presetDao(): PresetDao

    companion object {
        @Volatile
        private var INSTANCE: PresetDb? = null

        fun getDb(ctx: Context): PresetDb {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    ctx.applicationContext,
                    PresetDb::class.java,
                    "focusflowdb"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}