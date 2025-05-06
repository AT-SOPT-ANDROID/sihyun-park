package org.sopt.at

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.sopt.at.data.model.SignUpRequest
import org.sopt.at.data.model.SignUpResponse
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.sopt.at.remote.ServicePool

class MyViewModel : ViewModel() {
    private val _userId = MutableStateFlow("Unknown")
    val userId: StateFlow<String> get() = _userId.asStateFlow()

    fun setUserId(id: String) {
        _userId.value = id
    }

    fun signUp(
        loginId: String,
        password: String,
        nickname: String,
        onSuccess: (SignUpResponse) -> Unit,
        onFailure: (String) -> Unit
    ) {
        viewModelScope.launch {
            try {
                val response = ServicePool.userService.signUp(
                    SignUpRequest(loginId, password, nickname)
                )
                if (response.isSuccessful && response.body()?.success == true) {
                    onSuccess(response.body()!!)
                } else {
                    onFailure(response.body()?.message ?: "회원가입 실패")
                }
            } catch (e: Exception) {
                onFailure("네트워크 오류: ${e.message}")
            }
        }
    }
}
