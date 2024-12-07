package dev.seabat.kmp.tutorial

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.seabat.kmp.tutorial.shared.RocketLaunchShared
import dev.seabat.kmp.tutorial.shared.grep
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val _rocketLaunchPhrase = MutableStateFlow("")
    val rocketLaunchPhrase: StateFlow<String>
        get() = _rocketLaunchPhrase

    init {
        viewModelScope.launch {
            RocketLaunchShared().getLaunchPhraseFlow().collect { phrase ->
                _rocketLaunchPhrase.update {
                    phrase
                }
            }
        }
    }
}