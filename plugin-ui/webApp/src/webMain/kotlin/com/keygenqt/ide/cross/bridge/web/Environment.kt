package com.keygenqt.ide.cross.bridge.web

import com.keygenqt.ide.cross.bridge.Bridge
import com.keygenqt.ide.cross.bridge.ComposeResBridge
import com.keygenqt.ide.cross.bridge.Environment
import com.keygenqt.ide.cross.bridge.Platform
import com.keygenqt.ide.cross.bridge.PlatformView
import com.keygenqt.ide.cross.bridge.web.view.WebMainViewBridge
import com.keygenqt.ide.cross.bridge.web.view.WebSettingsViewBridge

/**
 * Web implementation of the runtime environment.
 * Wires the system bridge and view bridges for browser/VS Code Webview mode.
 */
data class WebEnvironment(
    /**
     * Sets the runtime platform to Web.
     */
    override val platform: Platform = Platform.Web,

    /**
     * Provides the bridge with web system hooks and Main/Settings view bridges.
     */
    override val bridge: Bridge = Bridge(
        res = ComposeResBridge(),
        sys = WebSysBridge(),
        vws = hashMapOf(
            PlatformView.Main to WebMainViewBridge(),
            PlatformView.Settings to WebSettingsViewBridge(),
        )
    )
) : Environment
