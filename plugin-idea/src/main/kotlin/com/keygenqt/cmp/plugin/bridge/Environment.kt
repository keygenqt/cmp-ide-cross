package com.keygenqt.cmp.plugin.bridge

import com.intellij.openapi.project.Project
import com.keygenqt.cmp.plugin.bridge.view.PluginMainViewBridge
import com.keygenqt.cmp.plugin.bridge.view.PluginSettingsViewBridge
import com.keygenqt.ide.cross.bridge.Bridge
import com.keygenqt.ide.cross.bridge.Environment
import com.keygenqt.ide.cross.bridge.Platform
import com.keygenqt.ide.cross.bridge.PlatformView

/**
 * IntelliJ plugin implementation of the runtime environment.
 * Wires the system bridge with IDE-specific hooks and view bridges for Main/Settings.
 */
data class PluginEnvironment(
    /**
     * Active IntelliJ project instance for IDE API access.
     */
    val project: Project,

    /**
     * Sets the runtime platform to IntelliJ Plugin.
     */
    override val platform: Platform = Platform.IntellJPlugin,

    /**
     * Provides the bridge with plugin system hooks and Main/Settings view bridges.
     */
    override val bridge: Bridge = Bridge(
        res = PluginResBridge(),
        sys = PluginSysBridge(project),
        vws = hashMapOf(
            PlatformView.Main to PluginMainViewBridge(),
            PlatformView.Settings to PluginSettingsViewBridge(),
        )
    )
) : Environment
