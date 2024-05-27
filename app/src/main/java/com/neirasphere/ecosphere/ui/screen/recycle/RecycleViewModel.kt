package com.neirasphere.ecosphere.ui.screen.recycle

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neirasphere.ecosphere.data.RecycleRepository
import com.neirasphere.ecosphere.domain.model.RecycleCategoryData
import com.neirasphere.ecosphere.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class RecycleViewModel(
    private val repository: RecycleRepository
): ViewModel() {
    private val _recycleCategoryState : MutableStateFlow<UiState<List<com.neirasphere.ecosphere.domain.model.RecycleCategoryData>>> = MutableStateFlow(
        UiState.Loading
    )

    val recycleCategoryState : StateFlow<UiState<List<com.neirasphere.ecosphere.domain.model.RecycleCategoryData>>>
        get()=_recycleCategoryState

    fun getRecycleCategoryState(){
        viewModelScope.launch {
            repository.getAllRecycleCategory()
                .catch {
                    _recycleCategoryState.value = UiState.Error(it.message.toString())
                }
                .collect{
                    _recycleCategoryState.value = UiState.Success(it)
                }
        }
    }
}