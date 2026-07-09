package com.keygenqt.ide.cross.bridge

import com.keygenqt.ide.cross.bridge.view.DesktopMainViewBridge
import com.keygenqt.ide.cross.bridge.view.DesktopSettingsViewBridge

/**
 * Desktop implementation of the runtime environment.
 * Wires the system bridge and view bridges for standalone Desktop mode.
 */
data class DesktopEnvironment(
    /**
     * Sets the runtime platform to Desktop.
     */
    override val platform: Platform = Platform.Desktop,

    /**
     * Provides the bridge with desktop system hooks and Main/Settings view bridges.
     */
    override val bridge: Bridge = Bridge(
        res = ComposeResBridge(),
        sys = DesktopSysBridge(),
        vws = hashMapOf(
            PlatformView.Main to DesktopMainViewBridge(),
            PlatformView.Settings to DesktopSettingsViewBridge(),
        )
    )
) : Environment
