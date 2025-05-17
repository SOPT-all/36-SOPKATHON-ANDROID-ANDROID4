package com.example.android4.core.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// 이것도 더미라고 생각해도 좋아요
@Serializable
data class BaseResponse<T>(
    @SerialName("status")
    val status: Int,
    @SerialName("message")
    val message: String,
    @SerialName("data")
    val data: T? = null
)
