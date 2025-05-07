package dev.ayanw.focusflow.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import dev.ayanw.focusflow.R

@Composable
fun TopBar(
    navButtonAction: () -> Unit = {},
) {
    Box(
        modifier = Modifier.padding(
            top = with(LocalDensity.current) { WindowInsets.statusBars.getTop(this).toDp() },
            start = with(LocalDensity.current) { WindowInsets.statusBars.getTop(this).toDp() / 2 }
        )
    ) {
        IconButton(onClick = { navButtonAction() }) {
            Icon(
                modifier = Modifier.size(34.dp),
                painter = painterResource(id = R.drawable.ic_menu),
                contentDescription = "Menu",
            )
        }
    }
}