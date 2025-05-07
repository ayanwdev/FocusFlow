package dev.ayanw.focusflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.ayanw.focusflow.ui.components.SideBar
import dev.ayanw.focusflow.ui.components.Timer
import dev.ayanw.focusflow.ui.components.TopBar
import dev.ayanw.focusflow.ui.theme.FocusFlowTheme
import dev.ayanw.focusflow.utils.timerControl.TimerMode
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            FocusFlowTheme {

                val drawerState = rememberDrawerState(initialValue = DrawerValue.Open)
                val scope = rememberCoroutineScope()

                ModalNavigationDrawer(
                    drawerState = drawerState,
                    drawerContent = {
                        SideBar()
                    }
                ) {
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        topBar = {
                            TopBar(navButtonAction = {
                                scope.launch {
                                    if (drawerState.isOpen) {
                                        drawerState.close()
                                    } else {
                                        drawerState.open()
                                    }
                                }
                            })
                        }
                    ) { innerPadding ->
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier =
                                Modifier
                                    .fillMaxSize()
                                    .padding(innerPadding),
                        ) {
                            Column(
                                modifier = Modifier
                                    .padding(top = 80.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                            ) {
                                Timer(
                                    modifier = Modifier.padding(innerPadding),
                                    mode = TimerMode.COUNTDOWN,
                                )
                            }
                        }
                    }

                }
            }
        }
    }
}
