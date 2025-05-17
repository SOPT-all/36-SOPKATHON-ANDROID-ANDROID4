package com.example.android4.data.service

import com.example.android4.core.network.BaseResponse
import com.example.android4.data.dto.response.CourseDetailResponseDto
import com.example.android4.data.dto.response.CuratorListResponseDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CuratorService {
    @GET("/api/curators")
    suspend fun getCuratorList(): BaseResponse<CuratorListResponseDto>

    @GET("/api/courses/{courseId}")
    suspend fun getCourseDetail(
        @Path("courseId") courseId: Long
    ): BaseResponse<CourseDetailResponseDto>
}
