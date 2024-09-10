package dev.seabat.kmp.tutorial.shared.repository

import dev.seabat.kmp.tutorial.shared.source.FakePlatformSource
import kotlin.test.Test
import kotlin.test.assertEquals

class PlatformRepositoryTest {
    @Test
    fun testGetPlatformName() {
        val repository = PlatformRepository(FakePlatformSource())
        val platformName = repository.getPlatformName()
        assertEquals(true, platformName.isNotEmpty())
    }
}