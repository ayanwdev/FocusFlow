package dev.ayanw.focusflow.utils.presetManager

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PresetDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(preset: TimerPreset)

    @Delete
    suspend fun deletePreset(preset: TimerPreset)

    @Query("SELECT * FROM TimerPreset WHERE id = :id")
    fun getPresetById(id: Int): Flow<TimerPreset>

    @Query("SELECT * FROM TimerPreset ORDER BY id ASC")
    fun getAllPresets(): Flow<List<TimerPreset>>

    @Query("SELECT COUNT(*) FROM TimerPreset")
    suspend fun getSize(): Int
}
