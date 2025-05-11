package dev.ayanw.focusflow.utils.presetManager

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface PresetDao {

    @Upsert
    suspend fun upsertPreset(preset: TimerPreset)

    @Delete
    suspend fun deletePreset(preset: TimerPreset)

    @Query("SELECT * FROM TimerPreset WHERE id = :id")
    suspend fun getPresetById(id: Int): Flow<TimerPreset>

    @Query("SELECT * FROM TimerPreset ORDER BY id ASC")
    suspend fun getAllPresets(): Flow<List<TimerPreset>>

}