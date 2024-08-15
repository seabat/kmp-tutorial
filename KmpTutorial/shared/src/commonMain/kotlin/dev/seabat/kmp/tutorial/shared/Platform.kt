package dev.seabat.kmp.tutorial.shared

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform