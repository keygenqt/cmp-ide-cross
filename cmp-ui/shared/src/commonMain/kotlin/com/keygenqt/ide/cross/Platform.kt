package com.keygenqt.ide.cross

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform