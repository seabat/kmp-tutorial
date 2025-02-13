package dev.seabat.kmp.tutorial.shared.usecase

import kotlinx.coroutines.flow.Flow

interface LoadRocketLaunchInfoUseCaseContract {
    operator fun invoke(): Flow<String>
}