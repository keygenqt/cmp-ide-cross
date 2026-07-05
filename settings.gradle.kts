@file:Suppress("UnstableApiUsage")

rootProject.name = "cmp-ide-cross"

/**
 * Enables typesafe project accessors (e.g. projects.shared instead of project(":shared")).
 */
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

/**
 * Repositories for resolving Gradle plugins.
 */
pluginManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

/**
 * Repositories for resolving project dependencies.
 */
dependencyResolutionManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
    }
}

/**
 * Automatically resolves and downloads compatible JDK if not present.
 */
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

/**
 * Project modules.
 */
include(":cmp-ui:shared")
include(":cmp-ui:desktopApp")
include(":cmp-ui:webApp")

/**
 * IntelliJ IDEA plugin demo — uses cmp-ui as a shared Compose Multiplatform dependency.
 */
includeBuild("cmp-plugin")
