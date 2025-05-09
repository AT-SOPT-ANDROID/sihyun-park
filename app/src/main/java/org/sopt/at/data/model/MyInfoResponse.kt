package org.sopt.at.data.model

import kotlinx.serialization.Serializable

@Serializable
data class MyInfoResponse(
    val success: Boolean,
    val code: String,
    val message: String,
    val data: NicknameData? =null
)

@Serializable
data class NicknameData(
    val nickname: String
)
