package dev.ayanw.focusflow.ui.components

import androidx.compose.foundation.layout.Box
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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun TopBar(
    navController: NavController,
    drawerState: DrawerState,
    drawerScope: CoroutineScope
) {

    Box(
        modifier = Modifier
            .padding(
                top = with(LocalDensity.current) { WindowInsets.statusBars.getTop(this).toDp() },
                start = 16.dp,
                end = 16.dp
            )
            .fillMaxWidth()
    ) {

        // Menu Button (Left)
        IconButton(
            modifier = Modifier.align(Alignment.CenterStart),
            onClick = {
                drawerScope.launch {
                    drawerState.open()
                }
            } //navButtonAction
        ) {
            Icon(
                modifier = Modifier.size(34.dp),
                imageVector = Icons.Rounded.Menu,
                contentDescription = "Menu",
            )
        }

        // Profile Button (Right)
//        if (currentRoute == "home") {
//            IconButton(
//                modifier = Modifier.align(Alignment.CenterEnd),
//                onClick = {
//                    navController.navigate("profile")
//                }
//            ) {
//                Icon(
//                    modifier = Modifier.size(32.dp),
//                    imageVector = Icons.Rounded.Person,
//                    contentDescription = "Profile",
//                )
//            }
//        }

    }
}