package dev.seabat.kmp.tutorial.page.grep

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.seabat.kmp.tutorial.shared.grep
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class GrepViewModel : ViewModel() {
    private val _grepResult = MutableStateFlow<List<String>>(listOf())
    val grepResult: StateFlow<List<String>>
        get() = _grepResult

    init {
        viewModelScope.launch {
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