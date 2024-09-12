package dev.seabat.kmp.tutorial.shared.repository

class FakeRecipeRepository : PlatformRepositoryContract {
    override fun getPlatformName(): String = "Android 34"
}