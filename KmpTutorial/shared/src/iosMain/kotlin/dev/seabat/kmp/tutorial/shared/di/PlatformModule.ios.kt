package dev.seabat.kmp.tutorial.shared.di

import dev.seabat.kmp.tutorial.shared.source.IOSPlatform
import dev.seabat.kmp.tutorial.shared.source.PlatformContract
import org.koin.dsl.module

actual val platformModule = module {
    single<PlatformContract> {
        IOSPlatform()
    }
}