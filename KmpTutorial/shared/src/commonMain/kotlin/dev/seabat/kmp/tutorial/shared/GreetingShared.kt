package dev.seabat.kmp.tutorial.shared

class GreetingShared {
    private val platform = getPlatform()

    fun greet(): String {
        return "Hello, ${platform.name}!"
    }
}