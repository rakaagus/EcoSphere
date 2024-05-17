package com.neirasphere.ecosphere.ui.screen.education

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neirasphere.ecosphere.data.local.EducationRepository
import com.neirasphere.ecosphere.model.FirstEducationData
import com.neirasphere.ecosphere.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FirstEducationViewModel(
        private val repository: EducationRepository
    ): ViewModel() {
        private val _firstEducation : MutableStateFlow<UiState<List<FirstEducationData>>> = MutableStateFlow(
            UiState.Loading
        )

    val firstEducation : StateFlow<UiState<List<FirstEducationData>>>
        get() = _firstEducation

    fun getFirstEducation(educationId : Long) {
        viewModelScope.launch {
            _firstEducation.value = UiState.Loading
            _firstEducation.value = UiState.Success(listOf(repository.getFirstEduById(educationId)))

        }
    }
}
