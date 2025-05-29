package dev.ayanw.focusflow.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Menu
import androidx.compose.material.icons.sharp.Person
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    drawerState: DrawerState,
    drawerScope: CoroutineScope
) {
    TopAppBar(
        title = { Text("Title") },
        navigationIcon = {
            IconButton(
                onClick = {
                    drawerScope.launch { drawerState.open() }
                }
            ) {
                Icon(
                    modifier = Modifier.size(28.dp),
                    imageVector = Icons.Sharp.Menu,
                    contentDescription = "Menu"
                )
            }
        },
        actions = {
            IconButton(
                onClick = { /* TODO: Open settings */ }
            ) {
                Icon(
                    modifier = Modifier.size(28.dp),
                    imageVector = Icons.Sharp.Person,
                    contentDescription = "Settings"
                )
            }
        }
    )
}