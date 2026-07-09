package com.keygenqt.ide.cross.bridge.web

import com.keygenqt.ide.cross.bridge.SysBridge
import kotlinx.browser.window

/**
 * Web implementation of the system bridge.
 * Notifications and dialogs are handled via postMessage or window.alert.
 */
class WebSysBridge : SysBridge {

    /**
     * Web notification handler — delegates to postMessage or window.alert.
     */
    override val showNotification: ((title: String, message: String) -> Unit) = { _, message ->
        window.alert(message)
    }

    /**
     * Web confirm dialog handler.
     */
    override val showConfirmDialog: ((title: String, message: String, onResult: (Boolean) -> Unit) -> Unit)? = null
}
