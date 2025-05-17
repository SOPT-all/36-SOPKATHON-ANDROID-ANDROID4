package com.example.android4.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CuratorListResponseDto(
    @SerialName("curatorList")
    val curatorList: List<CuratorDto>
)

@Serializable
data class CuratorDto(
    @SerialName("id")
    val id: Int,
    @SerialName("nickname")
    val nickname: String,
    @SerialName("description")
    val description: String,
    @SerialName("profileImageUrl")
    val profileImageUrl: String
)
