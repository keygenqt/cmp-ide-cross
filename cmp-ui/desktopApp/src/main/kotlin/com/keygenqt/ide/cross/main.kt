package com.keygenqt.ide.cross

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "cmp-ide-cross",
    ) {
        App()
    }
}