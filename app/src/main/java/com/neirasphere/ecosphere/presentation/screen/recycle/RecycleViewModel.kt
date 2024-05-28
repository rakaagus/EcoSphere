package com.neirasphere.ecosphere.presentation.screen.recycle

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neirasphere.ecosphere.data.repository.RecycleRepositoryImpl
import com.neirasphere.ecosphere.domain.model.RecycleCategoryData
import com.neirasphere.ecosphere.domain.repository.RecycleRepository
import com.neirasphere.ecosphere.presentation.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecycleViewModel @Inject constructor(
    private val repository: RecycleRepository
): ViewModel() {
    private val _recycleCategoryState : MutableStateFlow<UiState<List<RecycleCategoryData>>> = MutableStateFlow(
        UiState.Loading
    )

    val recycleCategoryState : StateFlow<UiState<List<RecycleCategoryData>>>
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