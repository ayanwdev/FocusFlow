package dev.ayanw.focusflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.ayanw.focusflow.pages.About
import dev.ayanw.focusflow.pages.Home
import dev.ayanw.focusflow.pages.Premium
import dev.ayanw.focusflow.pages.Presets
import dev.ayanw.focusflow.pages.Profile
import dev.ayanw.focusflow.pages.Settings
import dev.ayanw.focusflow.pages.Statistics
import dev.ayanw.focusflow.pages.Subjects
import dev.ayanw.focusflow.pages.Tags
import dev.ayanw.focusflow.ui.components.SideBar
import dev.ayanw.focusflow.ui.components.TopBar
import dev.ayanw.focusflow.ui.theme.FocusFlowTheme
import dev.ayanw.focusflow.utils.presetManager.PresetDb
import dev.ayanw.focusflow.utils.presetManager.initDefaultPresets

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)

        val presetDb = PresetDb.getDb(applicationContext)
        initDefaultPresets(presetDb)

        enableEdgeToEdge()

        setContent {
            FocusFlowTheme {

                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                val scope = rememberCoroutineScope()
                val navController = rememberNavController()

                ModalNavigationDrawer(
                    drawerState = drawerState,
                    drawerContent = {
                        SideBar(
                            navController = navController,
                            drawerState = drawerState,
                            drawerScope = scope,
                        )
                    }
                ) {
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        topBar = {
                            TopBar(
                                navController = navController,
                                drawerState = drawerState,
                                drawerScope = scope
                            )
                        }
                    ) { innerPadding ->
                        NavHost(
                            navController = navController,
                            startDestination = "presets",
                            modifier = Modifier.padding(innerPadding)
                        ) {
                            composable("home") { Home() }
                            composable("profile") { Profile() }
                            composable("statistics") { Statistics() }
                            composable("tags") { Tags() }
                            composable("subjects") { Subjects() }
                            composable("presets") { Presets() }
                            composable("settings") { Settings() }
                            composable("about") { About() }
                            composable("premium") { Premium() }
                        }
                    }

                }
            }
        }
    }
}
