package com.keygenqt.ide.cross

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.ComposeViewport
import com.keygenqt.ide.cross.bridge.vscode.VsCodeEnvironment
import com.keygenqt.ide.cross.bridge.web.WebEnvironment
import kotlinx.browser.document
import kotlinx.browser.window

/**
 * Web/JS entry point — mounts the Compose Multiplatform UI into the browser DOM via ComposeViewport.
 */
@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport {
        val isVsCode = isVsCodeWebView()
        val vsCodeApi: dynamic = if (isVsCode) js("acquireVsCodeApi()") else null
        InitApp(if (isVsCode) VsCodeEnvironment(vsCodeApi) else WebEnvironment()) {
            if (isVsCode) {
                VSExtensionTheme {
                    RootAppDispatcher()
                }
            } else {
                MaterialTheme {
                    RootAppDispatcher()
                }
            }
        }
    }
}

/**
 * Builds a Material 3 color scheme from VS Code CSS custom properties for native look and feel.
 */
@Composable
private fun VSExtensionTheme(content: @Composable () -> Unit) {
    val rootElement = document.documentElement ?: return
    val computedStyle = window.getComputedStyle(rootElement)

    // Resolves a CSS color token or falls back to a default value
    val getThemeColor = { key: String, fallback: String ->
        val rawColor = computedStyle.getPropertyValue(key).trim()
        Color(parseHexColor(rawColor.ifBlank { fallback }))
    }

    // Dynamic color palette mapped from VS Code theme tokens
    val vsCodeDynamicPalette = darkColorScheme(
        background = getThemeColor("--vscode-editorWidget-background", "#252526"),
        surface = getThemeColor("--vscode-editorWidget-background", "#252526"),
        surfaceContainer = getThemeColor("--vscode-input-background", "#3c3c3c"),
        surfaceVariant = getThemeColor("--vscode-input-background", "#3c3c3c"),
        primary = getThemeColor("--vscode-focusBorder", "#007fd4"),
        primaryContainer = getThemeColor("--vscode-focusBorder", "#007fd4"),
        onBackground = getThemeColor("--vscode-foreground", "#cccccc"),
        onSurface = getThemeColor("--vscode-foreground", "#cccccc"),
        onPrimary = getThemeColor("--vscode-strongForeground", "#ffffff")
    )

    // Apply the dynamic palette
    MaterialTheme(colorScheme = vsCodeDynamicPalette) {
        Surface(
            color = MaterialTheme.colorScheme.background,
            content = content
        )
    }
}

/**
 * Checks if running inside a VS Code WebView sandbox.
 */
private fun isVsCodeWebView(): Boolean {
    return js("typeof acquireVsCodeApi !== 'undefined'") as Boolean
}

/**
 * Parses a hex color string (3, 6, or 8 chars) into an ARGB Long value.
 */
private fun parseHexColor(hex: String): Long {
    val cleanHex = hex.trim().removePrefix("#")
    return when (cleanHex.length) {
        6 -> "FF$cleanHex".toLong(16)
        8 -> cleanHex.toLong(16)
        else -> 0xFF1E1E1E
    }
}
