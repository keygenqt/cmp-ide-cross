package com.keygenqt.ide.cross

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.keygenqt.ide.cross.bridge.DesktopEnvironment

/**
 * Desktop entry point — launches the Compose Multiplatform window with the Desktop environment.
 */
fun main() = application {
    if (System.getProperty("os.name").lowercase().contains("mac")) {
        System.setProperty("skiko.renderApi", "SOFTWARE")
        System.setProperty("skiko.metal.enabled", "false")
    }
    Window(
        onCloseRequest = ::exitApplication,
        title = "CMP IDE Cross",
    ) {
        InitApp(DesktopEnvironment()) {
            MaterialTheme {
                RootAppDispatcher()
            }
        }
    }
}
