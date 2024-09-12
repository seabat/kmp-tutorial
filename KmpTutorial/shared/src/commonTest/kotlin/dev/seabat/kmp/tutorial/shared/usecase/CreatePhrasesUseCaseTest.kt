package dev.seabat.kmp.tutorial.shared.usecase

import dev.seabat.kmp.tutorial.shared.repository.FakeRecipeRepository
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.coroutines.test.runTest

class CreatePhrasesUseCaseTest {
    private lateinit var useCase: CreatePhrasesUseCaseContract

    @Test
    fun testCreatePhrasesUseCase() {
        useCase = CreatePhrasesUseCase(FakeRecipeRepository())

        runTest {
            val phrases = useCase()
            assertEquals(phrases.size, 2)
            assertEquals(true, phrases[0].startsWith("Hello, Android 34!"))
            assertEquals(true, phrases[1].startsWith("There are only"))
        }
    }
}