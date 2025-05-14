package dev.ayanw.focusflow.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import dev.ayanw.focusflow.utils.presetManager.PresetDb


@Composable
fun Presets() {
    val context = LocalContext.current
    val presetDao = remember { PresetDb.getDb(context).presetDao() }
    val presetsFlow = remember { presetDao.getAllPresets() }
    val size = produceState(initialValue = 0, presetDao) {
        value = presetDao.getSize()
    }
    val presets = presetsFlow.collectAsState(initial = emptyList())

    Column {
        Text(text = "Presets: ${size.value}\n")
        presets.value.forEach { preset ->
            Text(text = "#${preset.id}: ${preset.name} : ${preset.color.value}")
        }
    }
}