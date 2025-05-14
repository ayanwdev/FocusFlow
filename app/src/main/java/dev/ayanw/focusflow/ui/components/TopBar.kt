package dev.ayanw.focusflow.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import dev.ayanw.focusflow.ui.theme.Quicksand_Bold
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun TopBar(
    navController: NavController,
    drawerState: DrawerState,
    drawerScope: CoroutineScope
) {

    val navBackStackEntry = navController.currentBackStackEntryAsState().value
    val route = navBackStackEntry?.destination?.route

    val screenTitle = when (route) {
        null -> "Error"
        "home" -> ""
        else -> route.replaceFirstChar { it.uppercase() }
    }

    Row(
        modifier = Modifier
            .padding(
                top = with(LocalDensity.current) { WindowInsets.statusBars.getTop(this).toDp() },
                start = 16.dp,
                end = 16.dp
            )
            .fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
    ) {
        // Menu Button (Left)
        IconButton(
            onClick = { // navButtonAction
                drawerScope.launch {
                    drawerState.open()
                }
            }
        ) {
            Icon(
                modifier = Modifier.size(30.dp),
                imageVector = Icons.Rounded.Menu,
                contentDescription = "Menu",
            )
        }
        Text(
            modifier = Modifier.padding(start = 8.dp, bottom = 2.dp),
            text = screenTitle,
            fontSize = 25.sp,
            fontFamily = Quicksand_Bold
        )
    }
}