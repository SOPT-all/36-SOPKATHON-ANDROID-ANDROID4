package com.example.android4.data.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CourseLikeRequestDto(
    @SerialName("courseId")
    val courseId: Long
)
