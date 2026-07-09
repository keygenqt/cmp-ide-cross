package com.keygenqt.ide.cross.bridge

/**
 * Runtime environment providing platform info and the bridge to host system APIs.
 */
interface Environment {
    val platform: Platform
    val bridge: Bridge
}
