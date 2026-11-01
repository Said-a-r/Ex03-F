package org.ucb.appp1

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.sentry.Sentry
import org.ucb.appp1.config.presentation.screen.ConfigScreen
import org.ucb.appp1.novedades.presentation.screen.NovedadScreen
import org.designsystem.project.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun App() {
    LaunchedEffect(Unit) {
        Sentry.init { options ->
            options.dsn = "https://2018f187a879f01915679615d340d459@o4512323078914048.ingest.us.sentry.io/4512323087171584"
            options.environment = "development"
        }
    }

    var selectedTab by remember { mutableStateOf(0) }

    AppTheme {
        Scaffold(
            bottomBar = {
                NavigationBar {
                    NavigationBarItem(
                        selected = selectedTab == 0,
                        onClick = { selectedTab = 0 },
                        label = { Text("E25-2: Config") },
                        icon = {}
                    )
                    NavigationBarItem(
                        selected = selectedTab == 1,
                        onClick = { selectedTab = 1 },
                        label = { Text("E50-4: Novedades") },
                        icon = {}
                    )
                }
            }
        ) { padding ->
            Box(modifier = Modifier.padding(padding)) {
                if (selectedTab == 0) {
                    ConfigScreen()
                } else {
                    NovedadScreen()
                }
            }
        }
    }
}
