package com.example.android4.data.di

import com.example.android4.data.service.CuratorService
import com.example.android4.data.service.RecommendCourseService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    @Singleton
    fun provideCuratorService(retrofit: Retrofit): CuratorService =
        retrofit.create(CuratorService::class.java)

    @Provides
    @Singleton
    fun provideRecommendCourseService(retrofit: Retrofit): RecommendCourseService =
        retrofit.create(RecommendCourseService::class.java)
}
