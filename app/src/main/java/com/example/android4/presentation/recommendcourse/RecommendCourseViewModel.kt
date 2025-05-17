package com.example.android4.presentation.recommendcourse

import androidx.compose.runtime.currentComposer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android4.data.service.RecommendCourseService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class RecommendCourse(

    val userId: Int = -1,
    val nickname: String = "",
    val curatorDescription: String= "",
    val courseId: Int =-1,
    val courseTitle: String="",
    val isBookmarked: Boolean = false,
    val courseDescription: String ="",
    val imageUrls: List<String> = listOf<String>(),
    val recordDate: String = ""




)
@HiltViewModel
class RecommendCourseViewModel @Inject constructor(
    private val recommendCourseService: RecommendCourseService
): ViewModel() {



    private val _state = MutableStateFlow(RecommendCourse())
    val state: StateFlow<RecommendCourse>
        get() = _state.asStateFlow()

    fun getRecommendCourse() {

        viewModelScope.launch {
            runCatching {
                recommendCourseService.getRecommendCourse()
            }.onSuccess { response ->
                val testList = response.data!!.courseList!!.map{

                }
                _state.update { recommendCourse ->
                    recommendCourse.copy(
                        nickname = it.data!!.nickname,
                        curatorDescription = it.data!!.description,
                        courseId = it.data!!.courseList.get(it.data.courseList.size),
                        courseTitle = it.data!!.courseList,
                        isBookmarked = it.data!!.courseList,
                        courseDescription = it.data!!.courseList,
                        imageUrls = it.data!!.courseList,
                        recordDate = it.data!!.courseList

                    )
                }
            }.onFailure {
                _state.update { recommendCourse->
                    recommendCourse.copy(
                        nickname = "네트워크 통신 실패"
                    )

                }
            }
        }
    }


}