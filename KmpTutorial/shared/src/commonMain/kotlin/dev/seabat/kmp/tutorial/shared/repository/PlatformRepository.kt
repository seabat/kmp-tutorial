package dev.seabat.kmp.tutorial.shared.repository

import dev.seabat.kmp.tutorial.shared.source.PlatformSourceContract

class PlatformRepository(private val platformSource: PlatformSourceContract) :
    PlatformRepositoryContract {
    override fun getPlatformName(): String = platformSource.getPlatformName()
}