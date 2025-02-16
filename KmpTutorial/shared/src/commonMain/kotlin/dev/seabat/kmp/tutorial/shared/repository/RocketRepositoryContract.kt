package dev.seabat.kmp.tutorial.shared.repository

interface RocketRepositoryContract {
    suspend fun launchPhrase(): String
}