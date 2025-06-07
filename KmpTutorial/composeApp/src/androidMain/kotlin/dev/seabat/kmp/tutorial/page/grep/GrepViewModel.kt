package dev.seabat.kmp.tutorial.page.grep

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.seabat.kmp.tutorial.shared.usecase.GrepUseCaseContract
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.getValue

class GrepViewModel : ViewModel(), KoinComponent {

    private val grepUseCase: GrepUseCaseContract by inject()
    private val _grepResult = MutableStateFlow<List<String>>(listOf())
    val grepResult: StateFlow<List<String>>
        get() = _grepResult

    init {
        viewModelScope.launch {
            grepUseCase(
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