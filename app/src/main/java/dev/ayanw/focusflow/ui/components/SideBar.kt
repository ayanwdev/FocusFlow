package dev.ayanw.focusflow.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.Label
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.rounded.Analytics
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material.icons.rounded.Stars
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import dev.ayanw.focusflow.ui.theme.Quicksand_Bold
import dev.ayanw.focusflow.ui.theme.TailWind
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

data class SideBarItem(
    val label: String,
    val icon: ImageVector,
    val color: Color = TailWind.Gray_100,
    val action: () -> Unit = {},
)

val items = listOf<SideBarItem>(
    SideBarItem(
        label = "Home",
        icon = Icons.Filled.Home,
    ),
    SideBarItem(
        label = "Profile",
        icon = Icons.Filled.Person,
    ),
    SideBarItem(
        label = "Tags",
        icon = Icons.AutoMirrored.Rounded.Label
    ),
    SideBarItem(
        label = "Statistics",
        icon = Icons.Rounded.Analytics
    ),
    SideBarItem(
        label = "Settings",
        icon = Icons.Rounded.Settings
    ),
    SideBarItem(
        label = "About",
        icon = Icons.Rounded.Info
    ),
    SideBarItem(
        label = "Premium",
        icon = Icons.Rounded.Stars,
        color = TailWind.Yellow_500
    )
)

@Composable
fun SideBar(
    navController: NavController,
    drawerState: DrawerState,
    drawerScope: CoroutineScope
) {

    var selected by remember { mutableStateOf(navController.currentDestination?.route) }

    Column(
        modifier = Modifier
            .background(color = TailWind.Slate_950)
            .width(width = (LocalWindowInfo.current.containerSize.width * 0.3).dp)
            .fillMaxSize()
            .padding(
                all = 16.dp,
            )
    ) {
        Spacer(Modifier.height(48.dp))
        items.forEach { item ->
            NavigationDrawerItem(
                label = {
                    Text(
                        text = item.label,
                        color = TailWind.Gray_300,
                        fontSize = 20.sp,
                        fontFamily = Quicksand_Bold
                    )
                },
                selected = (selected == item.label),
                onClick = {
                    selected = item.label
                    navController.navigate(item.label)
                    drawerScope.launch {
                        drawerState.close()
                    }
                },
                colors = NavigationDrawerItemDefaults.colors(
                    selectedContainerColor = TailWind.Slate_800,
                ),
                icon = {
                    Icon(
                        modifier = Modifier
                            .size(24.dp),
                        imageVector = item.icon,
                        contentDescription = item.label,
                        tint = item.color,
                    )
                }
            )
        }
    }
}