package com.keygenqt.ide.cross.di

import com.keygenqt.ide.cross.bridge.PlatformView
import com.keygenqt.ide.cross.view.main.MainViewModel
import com.keygenqt.ide.cross.view.settings.SettingsViewModel
import org.koin.dsl.module

/**
 * Top-level Koin module that aggregates logging and ViewModels.
 */
val appModule = module {
    // Kermit logging
    includes(loggingModule)

    // ViewModels
    single {
        MainViewModel(
            env = get(),
            view = PlatformView.Main,
            logger = get()
        )
    }
    single {
        SettingsViewModel(
            env = get(),
            view = PlatformView.Settings,
            logger = get()
        )
    }
}
