package dev.seabat.kmp.tutorial

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.seabat.kmp.tutorial.shared.RocketLaunchShared
import dev.seabat.kmp.tutorial.shared.grep
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _rocketLaunchPhrase = MutableStateFlow("")
    val rocketLaunchPhrase: StateFlow<String>
        get() = _rocketLaunchPhrase

    private val _grepResult = MutableStateFlow<List<String>>(listOf())
    val grepResult: StateFlow<List<String>>
        get() = _grepResult

    init {
        viewModelScope.launch {
            RocketLaunchShared().getLaunchPhraseFlow().collect { phrase ->
                _rocketLaunchPhrase.update {
                    phrase
                }
            }

            grep(
                listOf("123 abc", "abc 123", "123 ABC", "ABC 123"),
                "[a-z]+"
            ) { result ->
                _grepResult.update {
                    grepResult.value.toMutableList().apply { add(result) }
                }
            }
        }
    }
}