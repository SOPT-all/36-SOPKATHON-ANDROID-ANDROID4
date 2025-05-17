package com.example.android4.data.service

import com.example.android4.core.network.BaseResponse
import com.example.android4.data.dto.response.CuratorListResponseDto
import retrofit2.http.GET

interface CuratorService {
    @GET("/api/curators")
    suspend fun getCuratorList(): BaseResponse<CuratorListResponseDto>
}
