package com.example.android4.presentation.detailcourse.model

data class DetailCourseState(
    val isLike: Boolean,
    val courseName: String,
    val courseDescription: String,
    val date: String,
    val courseList: List<DetailCourse>
)