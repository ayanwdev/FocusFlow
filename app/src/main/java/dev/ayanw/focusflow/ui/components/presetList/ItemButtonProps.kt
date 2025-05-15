package dev.ayanw.focusflow.ui.components.presetList

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import dev.ayanw.focusflow.ui.theme.TailWind

data class ItemButtonProps(
    val icon: ImageVector = Icons.Filled.PlayArrow,
    val color: Color = TailWind.Slate_100,
    val action: () -> Unit = {},
)