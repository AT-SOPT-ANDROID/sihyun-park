package org.sopt.at

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.sopt.at.data.model.SignUpRequest
import org.sopt.at.data.model.SignUpResponse
import org.sopt.at.data.model.SignInRequest
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.sopt.at.remote.ServicePool
import androidx.lifecycle.viewmodel.compose.viewModel

class MyViewModel : ViewModel() {
    private val _userId = MutableStateFlow<Long?>(null)
    val userId: StateFlow<Long?> get() = _userId.asStateFlow()

    fun setUserId(id: Long) {
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

    fun signIn(
        loginId: String,
        password: String,
        onSuccess: (Long) -> Unit,
        onFailure: (String) -> Unit
    ) {
        viewModelScope.launch {
            try {
                val response = ServicePool.authService.signIn(SignInRequest(loginId, password))
                if (response.success && response.data != null) {
                    onSuccess(response.data.userId)
                } else {
                    onFailure(response.message)
                }
            } catch (e: Exception) {
                onFailure("네트워크 오류가 발생했어요: ${e.localizedMessage}")
            }
        }
    }

    fun getMyInfo(
        userId: Long,
        onSuccess: (String) -> Unit,
        onFailure: (String) -> Unit
    ) {
        viewModelScope.launch {
            try {
                val response = ServicePool.userService.getMyInfo(userId)
                if (response.isSuccessful && response.body()?.success == true) {
                    val nickname = response.body()?.data?.nickname ?: "닉네임 없음"
                    onSuccess(nickname)
                } else {
                    onFailure(response.body()?.message ?: "닉네임 조회 실패")
                }
            } catch (e: Exception) {
                onFailure("네트워크 오류: ${e.message}")
            }
        }
    }

}