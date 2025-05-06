package org.sopt.at.data.service

import org.sopt.at.data.model.SignInRequest
import org.sopt.at.data.model.SignInResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("/api/v1/auth/signin")
    suspend fun signIn(
        @Body request: SignInRequest
    ): SignInResponse
}
