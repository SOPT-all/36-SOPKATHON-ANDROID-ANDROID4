package com.example.android4.data.dto.response

import kotlinx.serialization.Serializable

data class BaseResponse<T>(
    val status: Int,
    val message: String,
    val data: T
)

@Serializable
data class MyPageDataDto(
    val nickname: String,
    val profileImageUrl: String,
    val courseList: List<MyPageCourseDto>
)

@Serializable
data class MyPageCourseDto(
    val courseTitle: String,
    val description: String,
    val imageUrls: List<String>
)
