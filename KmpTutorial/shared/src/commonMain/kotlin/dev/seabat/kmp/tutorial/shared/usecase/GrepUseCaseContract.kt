package dev.seabat.kmp.tutorial.shared.usecase

interface GrepUseCaseContract {
    operator fun invoke(lines: List<String>, pattern: String, action: (String) -> Unit)
}