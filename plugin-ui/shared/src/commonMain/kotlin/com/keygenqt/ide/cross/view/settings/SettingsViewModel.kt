package com.keygenqt.ide.cross.view.settings

import co.touchlab.kermit.Logger
import com.keygenqt.ide.cross.base.BaseViewModel
import com.keygenqt.ide.cross.bridge.Environment
import com.keygenqt.ide.cross.bridge.PlatformView

/**
 * ViewModel for the settings screen.
 */
class SettingsViewModel(
    env: Environment,
    view: PlatformView,
    logger: Logger
) : BaseViewModel(env, view) {
    init {
        logger.d { "platform: ${env.platform.name}" }
        logger.d { "view: ${view.name}" }
        logger.d { "bridge: ${bridge::class.simpleName}" }
    }
}
