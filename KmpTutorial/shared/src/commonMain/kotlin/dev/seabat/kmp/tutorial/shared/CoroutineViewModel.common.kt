package dev.seabat.kmp.tutorial.shared

import kotlinx.coroutines.CoroutineScope

expect abstract class CoroutineViewModel() {
    val coroutineScope: CoroutineScope

    fun dispose()

    protected open fun onCleared()
}