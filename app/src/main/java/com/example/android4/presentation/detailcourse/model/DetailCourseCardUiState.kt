package com.example.android4.presentation.detailcourse.model

data class DetailCourseCardUiState(
    val isLike: Boolean = false,
    val courseName: String = "",
    val courseDescription: String = "",
    val date: String = "",
    val courseList: List<DetailCourseModel> = emptyList()
)

data class DetailCourseUiState(
    val isLoading: Boolean = false,
    val data: DetailCourseCardUiState = DetailCourseCardUiState(),
    val error: String? = null
)
