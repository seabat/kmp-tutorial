package dev.seabat.kmp.tutorial.shared

class GreetingShared {
    private val platform = getPlatform()

    fun greet(): List<String> = buildList {
        add("Hello, ${platform.name}!")
        add(daysPhrase())
    }
}
