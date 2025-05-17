package com.example.android4.presentation.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class HomeCardUiState(
    val imageUrl: String,
    val userName: String,
    val userDescription: String
)

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    private val _cardList = MutableStateFlow<List<HomeCardUiState>>(
        listOf(
            HomeCardUiState(
                imageUrl = "https://avatars.githubusercontent.com/u/160750136?v=4",
                userName = "하동동동동님",
                userDescription = "하동에서 태어나고 29년째 거주중입니다. 여기 맛집 진심 다꿰고있어요...!! 먹는거 좋아하시는 분들께 추천합니다"
            ),
            HomeCardUiState(
                imageUrl = "https://avatars.githubusercontent.com/u/123456789?v=4",
                userName = "바다님",
                userDescription = "거제에서 태어나 자랐어요. 바다 관련 여행 코스 제가 다 알려드릴게요! 해산물 좋아하시면 제 코스 꼭 확인해보세요."
            ),
            HomeCardUiState(
                imageUrl = "https://avatars.githubusercontent.com/u/987654321?v=4",
                userName = "산골님",
                userDescription = "지리산 근처에서 자란 토박이입니다. 등산 코스와 숨겨진 휴양지를 소개해드릴게요. 자연 좋아하시는 분들께 추천!"
            )
        )
    )
    val cardList: StateFlow<List<HomeCardUiState>> = _cardList.asStateFlow()

    private val _currentPage = MutableStateFlow(0)
    val currentPage: StateFlow<Int> = _currentPage.asStateFlow()

    fun updateCurrentPage(page: Int) {
        _currentPage.value = page
    }
}
