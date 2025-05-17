package com.example.android4.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android4.data.service.CuratorService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class HomeCardUiState(
    val id: Int = 0,
    val imageUrl: String = "",
    val userName: String = "",
    val userDescription: String = ""
)

data class HomeUiState(
    val isLoading: Boolean = false,
    val cardList: List<HomeCardUiState> = emptyList(),
    val error: String? = null
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val curatorService: CuratorService
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private val _currentPage = MutableStateFlow(0)
    val currentPage: StateFlow<Int> = _currentPage.asStateFlow()

    init {
        getCuratorList()
    }

    private fun getCuratorList() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)

            runCatching {
                curatorService.getCuratorList()
            }.onSuccess { response ->
                val curatorList = response.data?.curatorList?.map { curator ->
                    HomeCardUiState(
                        id = curator.id,
                        imageUrl = curator.profileImageUrl,
                        userName = curator.nickname,
                        userDescription = curator.description
                    )
                } ?: emptyList()

                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    cardList = curatorList
                )
            }.onFailure { exception ->
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = exception.message ?: "큐레이터 목록을 불러오는데 실패했습니다."
                )
            }
        }
    }

    fun updateCurrentPage(page: Int) {
        _currentPage.value = page
    }
}
