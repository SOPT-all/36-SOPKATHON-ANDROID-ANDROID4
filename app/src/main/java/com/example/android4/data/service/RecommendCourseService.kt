package com.example.android4.data.service

import com.example.android4.core.network.BaseResponse
import com.example.android4.data.dto.response.RecommendResponseDto
import retrofit2.http.GET

interface RecommendCourseService {
    @GET("/api/users/{userId}/course")
    suspend fun getRecommendCourse(): BaseResponse<RecommendResponseDto>
}