package dev.seabat.kmp.tutorial.shared.source

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
