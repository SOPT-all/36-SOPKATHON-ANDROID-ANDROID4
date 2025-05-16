package com.example.android4.core.network

import com.example.android4.BuildConfig
import javax.inject.Inject
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

// 이건 헤더에 토큰 쓴다고 하면 사용하면 됩니다.
class AuthInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val authRequest = originalRequest.newBuilder().newAuthBuilder().build()

        val response = chain.proceed(authRequest)

        return response
    }

    private fun Request.Builder.newAuthBuilder() =
        this.addHeader(USER_ID, "${BuildConfig.USER_ID}")

    companion object {
        private const val USER_ID = "userId"
    }
}
