package com.neirasphere.ecosphere.ui.screen.education

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neirasphere.ecosphere.data.local.EducationRepository
import com.neirasphere.ecosphere.model.FourthEducationData
import com.neirasphere.ecosphere.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FourthEducationViewModel (
    private val repository: EducationRepository
): ViewModel() {
    private val _fourthEducation : MutableStateFlow<UiState<List<FourthEducationData>>> = MutableStateFlow(
        UiState.Loading
    )

    val fourthEducation : StateFlow<UiState<List<FourthEducationData>>>
        get() = _fourthEducation

    fun getFourthEducation(fourthEducationId : Long) {
        viewModelScope.launch {
            _fourthEducation.value = UiState.Loading
            _fourthEducation.value = UiState.Success(listOf(repository.getFourthEduById(fourthEducationId)))

        }
    }
}