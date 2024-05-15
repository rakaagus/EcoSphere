package com.neirasphere.ecosphere.ui.screen.education

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neirasphere.ecosphere.data.local.EducationRepository
import com.neirasphere.ecosphere.model.SecondEducationData
import com.neirasphere.ecosphere.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SecondEducationViewModel (
    private val repository: EducationRepository
): ViewModel() {
    private val _secondEducation : MutableStateFlow<UiState<List<SecondEducationData>>> = MutableStateFlow(
        UiState.Loading
    )

    val secondEducation : StateFlow<UiState<List<SecondEducationData>>>
        get() = _secondEducation

    fun getSecondEducation(secEducationId : Long) {
        viewModelScope.launch {
            _secondEducation.value = UiState.Loading
            _secondEducation.value = UiState.Success(listOf(repository.getSecondEduById(secEducationId)))

        }
    }
}