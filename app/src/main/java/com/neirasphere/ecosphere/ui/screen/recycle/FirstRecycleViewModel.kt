package com.neirasphere.ecosphere.ui.screen.recycle

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neirasphere.ecosphere.data.RecycleRepository
import com.neirasphere.ecosphere.model.FirstRecycleData
import com.neirasphere.ecosphere.model.RecycleCategoryData
import com.neirasphere.ecosphere.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class FirstRecycleViewModel(
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