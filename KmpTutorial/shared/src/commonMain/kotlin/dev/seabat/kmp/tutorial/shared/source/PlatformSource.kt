package dev.seabat.kmp.tutorial.shared.source

class PlatformSource : PlatformSourceContract {
    private val platform = getPlatform()
    override fun getPlatformName(): String = platform.name
}