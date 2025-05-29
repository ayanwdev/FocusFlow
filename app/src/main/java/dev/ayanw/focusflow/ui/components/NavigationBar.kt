package dev.ayanw.focusflow.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.Article
import androidx.compose.material.icons.automirrored.rounded.Label
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.rounded.Analytics
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.ayanw.focusflow.models.NavigationBarIcon

data class SelectedItem(
    val rowIndex: Int,
    val itemIndex: Int
)

@Composable
fun NavigationBar() {
    val navItems: List<List<NavigationBarIcon>> = listOf(
        listOf(
            NavigationBarIcon(
                label = "Home",
                icon = Icons.Filled.Home,
            ),
            NavigationBarIcon(
                label = "Profile",
                icon = Icons.Filled.Person,
            ),
            NavigationBarIcon(
                label = "Statistics",
                icon = Icons.Rounded.Analytics
            )
        ),
        listOf(
            NavigationBarIcon(
                label = "Presets",
                icon = Icons.AutoMirrored.Rounded.Article
            ),
            NavigationBarIcon(
                label = "Tags",
                icon = Icons.AutoMirrored.Rounded.Label
            ),
            NavigationBarIcon(
                label = "Subjects",
                icon = Icons.Rounded.Info
            )
        ),
        listOf(
            NavigationBarIcon(
                label = "Settings",
                icon = Icons.Rounded.Settings
            ),
            NavigationBarIcon(
                label = "About",
                icon = Icons.Rounded.Info
            ),
            NavigationBarIcon(
                label = "Premium",
                icon = Icons.Rounded.Star,
            )
        )
    )

    var (selection, setSelection) = remember {
        androidx.compose.runtime.mutableStateOf(
            SelectedItem(
                0,
                0
            )
        )
    }
    fun getSelection() = selection

    ModalDrawerSheet(
        drawerShape = RoundedCornerShape(0),
        windowInsets = WindowInsets(
            left = 16.dp,
            right = 16.dp,
            top = 64.dp
        )
    ) {
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            navItems.forEach { row ->
                if (row != navItems.first()) {
                    Spacer(modifier = Modifier.height(8.dp))
                    HorizontalDivider()
                    Spacer(modifier = Modifier.height(8.dp))
                }
                row.forEach { item ->
                    NavigationDrawerItem(
                        icon = { Icon(imageVector = item.icon, contentDescription = item.label) },
                        label = { Text(fontSize = 20.sp, text = item.label) },
                        selected = navItems.indexOf(row) == getSelection().rowIndex && row.indexOf(
                            item
                        ) == getSelection().itemIndex,
                        onClick = {
                            setSelection(SelectedItem(navItems.indexOf(row), row.indexOf(item)))
                        },
                    )
                }
            }
        }
    }
}

