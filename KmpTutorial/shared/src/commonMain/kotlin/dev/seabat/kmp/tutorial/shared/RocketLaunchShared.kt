package dev.seabat.kmp.tutorial.shared

import RocketComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RocketLaunchShared {
    private val rocketComponent by lazy { RocketComponent() }
    fun getLaunchPhraseFlow(): Flow<String> = flow {
        emit(rocketComponent.launchPhrase())
    }
}