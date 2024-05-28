package com.neirasphere.ecosphere.presentation.screen.education

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neirasphere.ecosphere.data.repository.EducationRepositoryImpl
import com.neirasphere.ecosphere.domain.model.FirstEducationData
import com.neirasphere.ecosphere.domain.repository.EducationRepository
import com.neirasphere.ecosphere.presentation.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FirstEducationViewModel @Inject constructor(
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
