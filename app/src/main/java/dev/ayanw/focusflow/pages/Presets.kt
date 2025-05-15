package dev.ayanw.focusflow.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import dev.ayanw.focusflow.ui.components.presetList.PresetListItem
import dev.ayanw.focusflow.utils.presetManager.PresetDb

@Composable
fun Presets() {
    val context = LocalContext.current
    val presetDao = remember { PresetDb.getDb(context).presetDao() }
    val presetsFlow = remember { presetDao.getAllPresets() }
    val presets = presetsFlow.collectAsState(initial = emptyList())

    Column {
        presets.value.forEach { preset ->
            PresetListItem(name = preset.name, color = preset.color)
        }
    }
}