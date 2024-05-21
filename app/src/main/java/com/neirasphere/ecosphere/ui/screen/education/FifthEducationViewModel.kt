package com.neirasphere.ecosphere.ui.screen.education

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neirasphere.ecosphere.data.local.EducationRepository
import com.neirasphere.ecosphere.model.FifthEducationData
import com.neirasphere.ecosphere.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FifthEducationViewModel (
    private val repository: EducationRepository
): ViewModel() {
    private val _fifthEducation : MutableStateFlow<UiState<List<FifthEducationData>>> = MutableStateFlow(
        UiState.Loading
    )

    val fifthEducation : StateFlow<UiState<List<FifthEducationData>>>
        get() = _fifthEducation

    fun getFifthEducation(fifthEducationId : Long) {
        viewModelScope.launch {
            _fifthEducation.value = UiState.Loading
            _fifthEducation.value = UiState.Success(listOf(repository.getFifthEduById(fifthEducationId)))

        }
    }
}