package dev.seabat.kmp.tutorial.shared.source

class PlatformSource(private val platform: PlatformContract) : PlatformSourceContract {
    override fun getPlatformName(): String = platform.name
}