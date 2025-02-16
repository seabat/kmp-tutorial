package dev.seabat.kmp.tutorial.shared.viewmodel

import dev.seabat.kmp.tutorial.shared.usecase.CreatePhrasesUseCaseContract
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GreetingSharedViewModel :
    CoroutineViewModel(),
    KoinComponent {
    private val createPhrasesUseCase: CreatePhrasesUseCaseContract by inject()

    private val _phrases = MutableStateFlow<List<String>>(emptyList())
    val phrases: StateFlow<List<String>> = _phrases

    /**
     * フレーズを読み込む (Android 向け)
     *
     * - Android は coroutine で取得した値を StateFlow で受け取る
     */
    fun loadPhrases() =
        coroutineScope.launch {
            _phrases.update {
                createPhrasesUseCase.invoke()
            }
        }

    /**
     * フレーズの監視を開始する (iOS 向け)
     *
     * - iOS は StateFlow を扱えないので coroutine で取得した値をクロージャの中で受け取る
     *
     * @param onChange
     */
    fun observePhrases(onChange: (List<String>) -> Unit) {
        coroutineScope.launch {
            onChange(
                createPhrasesUseCase.invoke()
            )
        }
    }
}