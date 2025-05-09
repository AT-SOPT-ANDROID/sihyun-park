package org.sopt.at.data.model

@kotlinx.serialization.Serializable
data class SignInRequest(
    val loginId: String,
    val password: String
)
