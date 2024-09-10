package dev.seabat.kmp.tutorial.shared.usecase

interface CreatePhrasesUseCaseContract {
    suspend operator fun invoke(): List<String>
}