package dev.seabat.kmp.tutorial.shared

import dev.seabat.kmp.tutorial.shared.repository.RocketRepositoryContract
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class RocketLaunchShared : KoinComponent {
    private val rocketRepository by inject<RocketRepositoryContract>()
    fun getLaunchPhraseFlow(): Flow<String> = flow {
        emit(rocketRepository.launchPhrase())
    }
}