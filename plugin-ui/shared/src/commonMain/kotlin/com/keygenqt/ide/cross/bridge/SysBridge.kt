package com.keygenqt.ide.cross.bridge

/**
 * Low-level operational core bridge mapping to host OS and IDE hooks.
 * Provides platform-agnostic access to notifications and modal dialogs.
 */
interface SysBridge {
    val showNotification: ((title: String, message: String) -> Unit)?
    val showConfirmDialog: ((title: String, message: String, onResult: (Boolean) -> Unit) -> Unit)?

    /**
     * Dispatches a native toast notification to the active host system.
     */
    fun showNotification(
        title: String,
        message: String,
    ) {
        showNotification?.invoke(title, message)
            ?: throw IllegalStateException("showNotification is not available on this platform")
    }

    /**
     * Invokes a modal confirmation dialog on the target platform.
     */
    fun showConfirmDialog(
        title: String,
        message: String,
        onResult: (Boolean) -> Unit
    ) {
        showConfirmDialog?.invoke(title, message, onResult)
            ?: throw IllegalStateException("showConfirmDialog is not available on this platform")
    }
}

/**
 * No-op [SysBridge] implementation for platforms without native system integrations.
 */
open class SysBridgeEmpty : SysBridge {
    override val showNotification: ((title: String, message: String) -> Unit)? = null
    override val showConfirmDialog: ((title: String, message: String, onResult: (Boolean) -> Unit) -> Unit)? = null
}
