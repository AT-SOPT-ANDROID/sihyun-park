package org.sopt.at.data.service

import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.Response
import org.sopt.at.data.model.SignUpRequest
import org.sopt.at.data.model.SignUpResponse

interface UserService {
    @POST("/api/v1/auth/signup")
    suspend fun signUp(
        @Body request: SignUpRequest
    ): Response<SignUpResponse>
}
