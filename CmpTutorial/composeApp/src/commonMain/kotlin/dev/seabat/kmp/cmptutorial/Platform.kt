package dev.seabat.kmp.cmptutorial

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform