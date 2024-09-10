package dev.seabat.kmp.tutorial.shared.usecase

import dev.seabat.kmp.tutorial.shared.daysPhrase
import dev.seabat.kmp.tutorial.shared.repository.PlatformRepositoryContract

class CreatePhrasesUseCase(private val platformRepository: PlatformRepositoryContract) :
    CreatePhrasesUseCaseContract {
    override suspend fun invoke(): List<String> = buildList {
        add("Hello, ${platformRepository.getPlatformName()}!")
        add(daysPhrase())
    }
}