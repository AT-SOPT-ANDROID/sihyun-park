package org.sopt.at.data.model

import kotlinx.serialization.Serializable

@Serializable
data class SignUpRequest(
    val loginId: String,
    val password: String,
    val nickname: String
)
