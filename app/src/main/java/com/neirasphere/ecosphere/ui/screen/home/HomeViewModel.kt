package com.neirasphere.ecosphere.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neirasphere.ecosphere.data.LearnRepository
import com.neirasphere.ecosphere.model.CategoryLearn
import com.neirasphere.ecosphere.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: LearnRepository): ViewModel() {
    private val _categoryLearn: MutableStateFlow<UiState<List<CategoryLearn>>> = MutableStateFlow(
        UiState.Loading
    )

    val categoryLearn: StateFlow<UiState<List<CategoryLearn>>>
        get() = _categoryLearn


    fun getLearnHome(){
        viewModelScope.launch {
            repository.getAllHomeLearnCategory()
                .catch {
                    _categoryLearn.value = UiState.Error(it.message.toString())
                }
                .collect{data ->
                    _categoryLearn.value = UiState.Success(data)
                }
        }
    }
}