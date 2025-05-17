package com.example.android4.data.service

import com.example.android4.core.network.BaseResponse
import com.example.android4.data.dto.response.CuratorListResponseDto
import com.example.android4.data.dto.response.MyPageDataDto
import retrofit2.http.GET
import retrofit2.http.Header

interface CuratorService {
    @GET("/api/curators")
    suspend fun getCuratorList(): BaseResponse<CuratorListResponseDto>

    @GET("/api/users/me")
    suspend fun getMyPage(
//        @Header("userId") userId: Long
    ): BaseResponse<MyPageDataDto>
}
