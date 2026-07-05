import org.jetbrains.intellij.platform.gradle.tasks.RunIdeTask
import java.io.PipedOutputStream
import java.io.PipedInputStream
import kotlin.concurrent.thread

plugins {
    id("org.jetbrains.kotlin.jvm")
    id("org.jetbrains.changelog")
    id("org.jetbrains.intellij.platform")
}

// Read more: https://plugins.jetbrains.com/docs/intellij/tools-intellij-platform-gradle-plugin.html
dependencies {
    testImplementation(libs.junit)

    // IntelliJ Platform Gradle Plugin Dependencies Extension - read more: https://plugins.jetbrains.com/docs/intellij/tools-intellij-platform-gradle-plugin-dependencies-extension.html
    intellijPlatform {
//        local("${System.getProperty("user.home")}/Applications/IntelliJ IDEA.app")
        intellijIdea("2026.1.4")

        // Add plugin dependencies for compilation here:
        bundledPlugin("com.intellij.java")
        bundledPlugin("org.jetbrains.kotlin")
    }
}

/**
 * Filters runIde output to only show lines containing the project's group tag.
 */
tasks.withType<RunIdeTask>().configureEach {
    val filterTag = project.group.toString()
    doFirst {
        val pos = PipedOutputStream()
        errorOutput = pos
        thread {
            PipedInputStream(pos).bufferedReader().forEachLine { line ->
                if (filterTag in line) {
                    System.err.println(line)
                }
            }
        }
    }
}
