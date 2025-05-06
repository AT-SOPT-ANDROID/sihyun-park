package org.sopt.at.data.model

@kotlinx.serialization.Serializable
data class SignInResponse(
    val success: Boolean,
    val code: String,
    val message: String,
    val data: SignInUserData?
)

@kotlinx.serialization.Serializable
data class SignInUserData(
    val userId: Long
)