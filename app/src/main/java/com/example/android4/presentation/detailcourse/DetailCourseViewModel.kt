package com.example.android4.presentation.detailcourse

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.android4.data.dto.request.CourseLikeRequestDto
import com.example.android4.data.service.CuratorService
import com.example.android4.presentation.detailcourse.model.DetailCourseCardUiState
import com.example.android4.presentation.detailcourse.model.DetailCourseModel
import com.example.android4.presentation.detailcourse.model.DetailCourseUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@HiltViewModel
class DetailCourseViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val curatorService: CuratorService
) : ViewModel() {
    private val _uiState = MutableStateFlow(DetailCourseUiState())
    val uiState: StateFlow<DetailCourseUiState> = _uiState.asStateFlow()

    val course = savedStateHandle.toRoute<DetailCourse>()

    val courseId = course.courseId.toLong() //TODO: 이전 화면에서 넘겨온 courseId 세팅

    init {
        getDetailCourse()
    }

    private fun getDetailCourse() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)

            runCatching {
                curatorService.getCourseDetail(
                    courseId = courseId
                )
            }.onSuccess { response ->
                val result = response.data
                val courseInfo = DetailCourseCardUiState(
                    courseName = result!!.courseTitle,
                    courseDescription = "연휴를 마무리하기 좋은 통영의 감각적인 \n" +
                            "공간들을 소개해 드릴게요!",
                    date = LocalDateTime.now().basicDateFormatter(),
                    isLike = false,
                    courseList = result.spotList.map {
                        DetailCourseModel(
                            name = it.spotName,
                            address = it.address,
                            description = it.description,
                            imageUrls = it.imageUrls
                        )
                    }
                )
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    data = courseInfo
                )
            }
        }
    }

    fun toggleBookmark() {
        val isLike = _uiState.value.data.isLike
        if (isLike) { // 좋아요 취소
            viewModelScope.launch {
                runCatching {
                    curatorService.deleteCourseLike(
                        courseId = courseId
                    )
                }.onSuccess {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        data = _uiState.value.data.copy(
                            isLike = !isLike
                        )
                    )
                }
            }
        } else { // 좋아요
            viewModelScope.launch {
                runCatching {
                    curatorService.postCourseLike(
                        CourseLikeRequestDto(courseId = courseId)
                    )
                }.onSuccess {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        data = _uiState.value.data.copy(
                            isLike = !isLike
                        )
                    )
                }
            }
        }
    }
}

fun LocalDateTime.basicDateFormatter(): String {
    return this.format(DateTimeFormatter.ofPattern("yyyy.MM.dd"))
}

