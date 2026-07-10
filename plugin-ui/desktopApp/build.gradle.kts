import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
}

dependencies {
    // Shared Compose UI code and platform resources
    implementation(projects.pluginUi.shared)
    implementation(projects.pluginUi.resources)

    // Compose Desktop application runtime for the current OS
    implementation(compose.desktop.currentOs)

    // Material3 theming and IDE preview tooling support
    implementation(libs.compose.material3)
    implementation(libs.compose.uiToolingPreview)

    // Navigation between screens
    implementation(libs.navigation3.ui)

    // Swing dispatcher for coroutines, Koin DI for Compose
    implementation(libs.kotlinx.coroutinesSwing)
    implementation(libs.koin.compose)
}

/**
 * Desktop application configuration — native distributions for macOS, Windows, and Linux.
 */
compose.desktop {
    application {
        mainClass = "com.keygenqt.ide.cross.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "com.keygenqt.ide.cross"
            packageVersion = "1.0.0"
        }
    }
}

/**
 * Suppress native access warnings for Skiko on JVM 21+.
 */
tasks.withType<JavaExec>().configureEach {
    jvmArgs = listOf(
        "--enable-native-access=ALL-UNNAMED",
    )
}
