import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
}

dependencies {
    implementation(projects.pluginUi.shared)
    implementation(projects.pluginUi.resources)

    implementation(compose.desktop.currentOs)
    implementation(libs.kotlinx.coroutinesSwing)
    implementation(libs.compose.uiToolingPreview)

    implementation(libs.compose.material3)
    implementation(libs.navigation3.ui)
    implementation(libs.androidx.lifecycle.viewmodelCompose)
    implementation(libs.androidx.lifecycle.runtimeCompose)

    implementation(libs.koin.compose)
}

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
