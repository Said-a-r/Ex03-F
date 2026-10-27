package org.ucb.appp1

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import io.sentry.Sentry
import org.ucb.appp1.novedades.presentation.screen.NovedadScreen
import org.designsystem.project.theme.AppTheme

@Composable
@Preview
fun App() {
    LaunchedEffect(Unit) {
        Sentry.init { options ->
            options.dsn = "https://2018f187a879f01915679615d340d459@o4512323078914048.ingest.us.sentry.io/4512323087171584"
            options.environment = "development"
        }
    }

    AppTheme {
        NovedadScreen()
    }
}
