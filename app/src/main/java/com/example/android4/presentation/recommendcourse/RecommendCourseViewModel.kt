package com.example.android4.presentation.recommendcourse

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.android4.data.dto.request.CourseLikeRequestDto
import com.example.android4.data.service.RecommendCourseService
import com.example.android4.data.dto.response.CourseListDto
import com.example.android4.data.service.CuratorService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class RecommendCourseUiState(
    val curatorNickname: String = "",
    val curatorDescription: String = "",
    val profileImageUrl: String = "",
    val courses: List<CourseUiState> = emptyList()
)

data class CourseUiState(
    val courseId: Int = -1,
    val courseTitle: String = "",
    val isBookmarked: Boolean = false,
    val description: String = "",
    val imageUrls: List<String> = emptyList(),
    val recordDate: String = ""
)

@HiltViewModel
class RecommendCourseViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val curatorService: CuratorService,
    private val recommendCourseService: RecommendCourseService
): ViewModel() {

    val userId = savedStateHandle.toRoute<RecommendCourse>()

    private val _state = MutableStateFlow(RecommendCourseUiState())
    val state: StateFlow<RecommendCourseUiState>
        get() = _state.asStateFlow()

    init {
        getRecommendCourse(userId = userId.userId)
    }

    fun getRecommendCourse(userId: Int) {
        viewModelScope.launch {
            runCatching {
                recommendCourseService.getRecommendCourse(userId)
            }.onSuccess { response ->
                response.data?.let { data ->
                    _state.update { 
                        it.copy(
                            curatorNickname = data.nickname,
                            curatorDescription = data.description,
                            profileImageUrl = data.profileImageUrl,
                            courses = data.courseList.map { course ->
                                CourseUiState(
                                    courseId = course.courseId,
                                    courseTitle = course.courseTitle,
                                    isBookmarked = course.isBookmarked,
                                    description = course.description,
                                    imageUrls = course.imageUrls,
                                    recordDate = course.recordDate
                                )
                            }
                        )
                    }
                }
            }.onFailure {
                _state.update { 
                    it.copy(
                        curatorNickname = "네트워크 통신 실패"
                    )
                }
            }
        }
    }

    fun toggleBookmark(courseId: Int) {
        val currentCourses = _state.value.courses
        val courseIndex = currentCourses.indexOfFirst { it.courseId == courseId }
        
        if (courseIndex != -1) {
            val course = currentCourses[courseIndex]
            val isBookmarked = course.isBookmarked
            
            viewModelScope.launch {
                if (isBookmarked) {
                    runCatching {
                        curatorService.deleteCourseLike(courseId = courseId.toLong())
                    }.onSuccess {
                        updateBookmarkState(courseId, !isBookmarked)
                    }
                } else {
                    runCatching {
                        curatorService.postCourseLike(
                            CourseLikeRequestDto(courseId = courseId.toLong())
                        )
                    }.onSuccess {
                        updateBookmarkState(courseId, !isBookmarked)
                    }
                }
            }
        }
    }
    
    private fun updateBookmarkState(courseId: Int, isBookmarked: Boolean) {
        _state.update { currentState ->
            val updatedCourses = currentState.courses.map { course ->
                if (course.courseId == courseId) {
                    course.copy(isBookmarked = isBookmarked)
                } else {
                    course
                }
            }
            currentState.copy(courses = updatedCourses)
        }
    }
}
