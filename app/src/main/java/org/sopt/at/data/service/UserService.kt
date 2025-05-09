package org.sopt.at.data.service

import org.sopt.at.data.model.MyInfoResponse
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.Response
import org.sopt.at.data.model.SignUpRequest
import org.sopt.at.data.model.SignUpResponse
import retrofit2.http.GET
import retrofit2.http.Header

interface UserService {
    @POST("/api/v1/auth/signup")
    suspend fun signUp(
        @Body request: SignUpRequest
    ): Response<SignUpResponse>

    @GET("/api/v1/users/me")
    suspend fun getMyInfo(
        @Header("userId") userId: Long
    ): Response<MyInfoResponse>
}
