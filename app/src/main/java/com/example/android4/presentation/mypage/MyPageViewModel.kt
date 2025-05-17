package com.example.android4.presentation.mypage

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android4.data.model.Course
import com.example.android4.data.model.UserProfile
import com.example.android4.data.service.CuratorService
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val service: CuratorService
) : ViewModel() {

    private val _userProfile = MutableStateFlow(UserProfile("", ""))
    val userProfile = _userProfile.asStateFlow()

    private val _courses = MutableStateFlow<List<Course>>(emptyList())
    val courses = _courses.asStateFlow()

    fun fetchMyPage(userId: Long) {
        viewModelScope.launch {
            try {
                val response = service.getMyPage()
                Log.d("MyPageViewModel", "response: $response")

                response.data?.let { data ->
                    Log.d("MyPageViewModel", "nickname: ${data.nickname}")
                    Log.d("MyPageViewModel", "courses: ${data.courseList}")

                    _userProfile.value = UserProfile(
                        name = data.nickname,
                        imageUrl = data.profileImageUrl
                    )
                    _courses.value = data.courseList.map {
                        Course(
                            title = it.courseTitle,
                            description = it.description,
                            imageUrls = it.imageUrls
                        )
                    }
                }
            } catch (e: Exception) {
                Log.d("tag", e.toString())
                // TODO: 에러 처리
            }
        }
    }
}
