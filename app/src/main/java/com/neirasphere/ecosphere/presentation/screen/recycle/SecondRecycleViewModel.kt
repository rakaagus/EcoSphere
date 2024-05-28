package com.neirasphere.ecosphere.presentation.screen.recycle

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neirasphere.ecosphere.data.repository.RecycleRepositoryImpl
import com.neirasphere.ecosphere.domain.model.SecondRecycleData
import com.neirasphere.ecosphere.domain.repository.RecycleRepository
import com.neirasphere.ecosphere.presentation.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SecondRecycleViewModel @Inject constructor(
    private val repository: RecycleRepository
): ViewModel() {
    private val _secondRecycleState : MutableStateFlow<UiState<List<SecondRecycleData>>> = MutableStateFlow(
        UiState.Loading
    )

    val secondRecycleState : StateFlow<UiState<List<SecondRecycleData>>>
        get() = _secondRecycleState

    fun getSecondRecycleState(contentId : Int){
        viewModelScope.launch {
            repository.getSecondRecycleListById(contentId)
                .catch {
                    _secondRecycleState.value = UiState.Error(it.message.toString())
                }
                .collect{
                    _secondRecycleState.value = UiState.Success(it)
                }
        }
    }
}