package com.neirasphere.ecosphere.presentation.screen.education

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neirasphere.ecosphere.data.repository.EducationRepositoryImpl
import com.neirasphere.ecosphere.domain.model.FourthEducationData
import com.neirasphere.ecosphere.domain.repository.EducationRepository
import com.neirasphere.ecosphere.presentation.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FourthEducationViewModel @Inject constructor(
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