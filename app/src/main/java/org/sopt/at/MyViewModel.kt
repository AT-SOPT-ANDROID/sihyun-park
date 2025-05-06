package org.sopt.at

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MyViewModel : ViewModel() {
    private val _userId = MutableStateFlow("Unknown")
    val userId: StateFlow<String> get() = _userId.asStateFlow()

    fun setUserId(id: String) {
        _userId.value = id
    }
}
