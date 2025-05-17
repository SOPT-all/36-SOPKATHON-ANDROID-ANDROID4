package com.example.android4.data.service

import com.example.android4.core.network.BaseResponse
import com.example.android4.data.dto.response.DummyResponseDto
import retrofit2.http.GET

interface DummyService {
    @GET("/api/v1/users/me")
    suspend fun getUserName(): BaseResponse<DummyResponseDto>
}



