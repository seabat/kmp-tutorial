package dev.seabat.kmp.tutorial.shared.di

import dev.seabat.kmp.tutorial.shared.usecase.GrepUseCaseContract
import dev.seabat.kmp.tutorial.shared.usecase.LoadRocketLaunchInfoUseCaseContract
import dev.seabat.kmp.tutorial.shared.viewmodel.GreetingSharedViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

/**
 * iOS 用に Koin の inject() を実行するクラス
 */
class KoinHelper : KoinComponent {
    val loadRocketLaunchInfoUseCase: LoadRocketLaunchInfoUseCaseContract by inject()

    val grepUseCase: GrepUseCaseContract by inject()

    val greetingSharedViewModel: GreetingSharedViewModel by inject()

}

fun getGreetingSharedViewModel(): GreetingSharedViewModel {
    return KoinHelper().greetingSharedViewModel // Koin の get() をブリッジ
}

fun getLoadRocketLaunchInfoUseCase(): LoadRocketLaunchInfoUseCaseContract {
    return KoinHelper().loadRocketLaunchInfoUseCase // Koin の get() をブリッジ
}

fun getGrepUseCase(): GrepUseCaseContract {
    return KoinHelper().grepUseCase // Koin の get() をブリッジ
}
