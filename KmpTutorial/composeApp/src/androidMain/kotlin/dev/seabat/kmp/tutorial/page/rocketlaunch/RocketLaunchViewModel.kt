package dev.seabat.kmp.tutorial.page.rocketlaunch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.seabat.kmp.tutorial.shared.usecase.LoadRocketLaunchInfoUseCaseContract
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class RocketLaunchViewModel : ViewModel(), KoinComponent {

    private val loadRocketLaunchInfoUseCase: LoadRocketLaunchInfoUseCaseContract by inject()

    private val _rocketLaunchPhrase = MutableStateFlow("")
    val rocketLaunchPhrase: StateFlow<String>
        get() = _rocketLaunchPhrase

    init {
        viewModelScope.launch {
            loadRocketLaunchInfoUseCase().collect { phrase ->
                _rocketLaunchPhrase.update {
                    phrase
                }
            }
        }
    }
}