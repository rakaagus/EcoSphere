package com.neirasphere.ecosphere.presentation.screen.education

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neirasphere.ecosphere.data.EducationRepository
import com.neirasphere.ecosphere.domain.model.EducationData
import com.neirasphere.ecosphere.presentation.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class EducationViewModel (
    private val repository: EducationRepository
): ViewModel() {
    private val _education : MutableStateFlow<UiState<List<EducationData>>> = MutableStateFlow(
        UiState.Loading
    )

    val education : StateFlow<UiState<List<EducationData>>>
        get() = _education

    fun getEducation(){
        viewModelScope.launch {
            repository.getAllEducationData()
                .catch {
                    _education.value = UiState.Error(it.message.toString())
                }
                .collect{
                    _education.value = UiState.Success(it)
                }
        }
    }
}