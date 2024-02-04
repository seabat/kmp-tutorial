package dev.seabat.kmp.tutorial

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform