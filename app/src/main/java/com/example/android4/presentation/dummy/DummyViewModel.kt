package com.example.android4.presentation.dummy

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android4.data.service.DummyService
import com.example.android4.presentation.dummy.model.DummyState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class DummyViewModel @Inject constructor(
    private val dummyService: DummyService
) : ViewModel() {

    private val _state = MutableStateFlow(DummyState())
    val state: StateFlow<DummyState>
        get() = _state.asStateFlow()

    fun getNickname() {
        viewModelScope.launch {
            runCatching {
                dummyService.getUserName()
            }.onSuccess {
                _state.update { dummyState ->
                    dummyState.copy(
                        nickname = it.data!!.nickname
                    )
                }
            }.onFailure {
                _state.update { dummyState ->
                    dummyState.copy(
                        nickname = "실패"
                    )
                }
            }
        }
    }
}
