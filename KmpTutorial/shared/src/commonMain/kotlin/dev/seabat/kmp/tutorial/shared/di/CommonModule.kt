package dev.seabat.kmp.tutorial.shared.di

import dev.seabat.kmp.tutorial.shared.repository.PlatformRepository
import dev.seabat.kmp.tutorial.shared.repository.PlatformRepositoryContract
import dev.seabat.kmp.tutorial.shared.repository.RocketRepository
import dev.seabat.kmp.tutorial.shared.repository.RocketRepositoryContract
import dev.seabat.kmp.tutorial.shared.source.PlatformSource
import dev.seabat.kmp.tutorial.shared.source.PlatformSourceContract
import dev.seabat.kmp.tutorial.shared.usecase.CreatePhrasesUseCase
import dev.seabat.kmp.tutorial.shared.usecase.CreatePhrasesUseCaseContract
import dev.seabat.kmp.tutorial.shared.usecase.GrepUseCase
import dev.seabat.kmp.tutorial.shared.usecase.GrepUseCaseContract
import dev.seabat.kmp.tutorial.shared.usecase.LoadRocketLaunchInfoUseCase
import dev.seabat.kmp.tutorial.shared.usecase.LoadRocketLaunchInfoUseCaseContract
import dev.seabat.kmp.tutorial.shared.viewmodel.GreetingSharedViewModel
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

// inject を実行するクラスを定義する
val viewModelModule = module {
    single { GreetingSharedViewModel() }
}

val useCaseModule = module {
    single<CreatePhrasesUseCaseContract> { CreatePhrasesUseCase(get()) }
    single<GrepUseCaseContract> { GrepUseCase() }
    single<LoadRocketLaunchInfoUseCaseContract> { LoadRocketLaunchInfoUseCase(get()) }
}

val repositoryModule = module {
    single<PlatformSourceContract> { PlatformSource(get()) }
    single<PlatformRepositoryContract> { PlatformRepository(get()) }
    single<RocketRepositoryContract> { RocketRepository() }
}

val koinModules = listOf(
    viewModelModule,
    useCaseModule,
    repositoryModule,
    platformModule
)

// for Android
fun initKoin(appDeclaration: KoinAppDeclaration) = startKoin {
    appDeclaration()
    modules(
        koinModules
    )
}

// for iOS
// Kotlin の関数は  [ファイル名]Kt.do[関数名]() として Swift から呼び出す。
// CommonModuleKt.doInitKoin()
fun initKoin() {
    startKoin {
        modules(
            koinModules
        )
    }
}
