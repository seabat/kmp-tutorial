@file:Suppress("ktlint:standard:filename")

package dev.seabat.kmp.tutorial.shared

import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals

class GrepTest {
    companion object {
        val sampleData = listOf(
            "123 abc",
            "abc 123",
            "123 ABC",
            "ABC 123"
        )
    }

    @Test
    fun shouldFindLowerCaseLetter() {
        val results = mutableListOf<String>()
        grep(sampleData, "[a-z]+") {
            results.add(it)
        }

        assertEquals(2, results.size)
        for (result in results) {
            assertContains(result, "abc")
        }
    }

    @Test
    fun shouldFindUpperCaseLetter() {
        val results = mutableListOf<String>()
        grep(sampleData, "[A-Z]+") {
            results.add(it)
        }

        assertEquals(2, results.size)
        for (result in results) {
            assertContains(result, "ABC")
        }
    }
}