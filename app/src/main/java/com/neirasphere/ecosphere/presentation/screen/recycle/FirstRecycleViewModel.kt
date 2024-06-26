package com.neirasphere.ecosphere.presentation.screen.recycle

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neirasphere.ecosphere.data.repository.RecycleRepositoryImpl
import com.neirasphere.ecosphere.domain.model.FirstRecycleData
import com.neirasphere.ecosphere.domain.repository.RecycleRepository
import com.neirasphere.ecosphere.presentation.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FirstRecycleViewModel @Inject constructor(
    private val repository: RecycleRepository
): ViewModel() {
    private val _firstRecycleState : MutableStateFlow<UiState<List<FirstRecycleData>>> = MutableStateFlow(
        UiState.Loading
    )

    val firstRecycleState : StateFlow<UiState<List<FirstRecycleData>>>
        get() = _firstRecycleState

    fun getFirstRecycle(contentId: Long) {
        viewModelScope.launch {
            repository.getFirstRecycleListByCategoryId(contentId)
                .catch {
                    _firstRecycleState.value = UiState.Error(it.message.toString())
                }
                .collect{
                    _firstRecycleState.value = UiState.Success(it)
                }
        }
    }
}