package com.example.android4.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecommendResponseDto(
    @SerialName("nickname")
    val nickname: String,
    @SerialName("description")
    val description: String,
    @SerialName("profileImageUrl")
    val profileImageUrl: String,
    @SerialName("courseList")
    val courseList: List<CourseListDto>
)

@Serializable
data class CourseListDto(
    @SerialName("courseId")
    val courseId: Int,
    @SerialName("courseTitle")
    val courseTitle: String,
    @SerialName("isBookmarked")
    val isBookmarked: Boolean,
    @SerialName("description")
    val description: String,
    @SerialName("imageUrls")
    val imageUrls: List<String>,
    @SerialName("recordDate")
    val recordDate: String

)



