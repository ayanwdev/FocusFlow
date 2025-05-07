package dev.ayanw.focusflow.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.dp
import dev.ayanw.focusflow.ui.theme.TailWind

@Composable
fun SideBar() {
    Box(
        modifier = Modifier
            .background(color = TailWind.Slate_950)
            .height(height = LocalWindowInfo.current.containerSize.height.dp)
            .width(width = (LocalWindowInfo.current.containerSize.width * 0.3).dp)
    ) {
        Text(text = "SideBar")
    }
}