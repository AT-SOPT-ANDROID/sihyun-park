package org.sopt.at.data.model

import kotlinx.serialization.Serializable

@Serializable
data class SignUpResponse(
    val success: Boolean,
    val code: String,
    val message: String,
    val data: SignUpUserData?
)

@Serializable
data class SignUpUserData(
    val userId: Int,
    val nickname: String
)
