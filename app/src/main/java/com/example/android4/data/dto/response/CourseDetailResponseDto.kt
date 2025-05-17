package com.example.android4.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CourseDetailResponseDto(
    @SerialName("nickname")
    val nickname: String,
    @SerialName("courseTitle")
    val courseTitle: String,
    @SerialName("spotList")
    val spotList: List<SpotDto>

)

@Serializable
data class SpotDto(
    @SerialName("spotName")
    val spotName: String,
    @SerialName("description")
    val description: String,
    @SerialName("address")
    val address: String,
    @SerialName("imageUrls")
    val imageUrls: List<String>
)