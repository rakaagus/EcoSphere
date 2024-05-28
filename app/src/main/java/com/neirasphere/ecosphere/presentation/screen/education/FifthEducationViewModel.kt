package com.neirasphere.ecosphere.presentation.screen.education

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neirasphere.ecosphere.data.repository.EducationRepositoryImpl
import com.neirasphere.ecosphere.domain.model.FifthEducationData
import com.neirasphere.ecosphere.domain.repository.EducationRepository
import com.neirasphere.ecosphere.presentation.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FifthEducationViewModel @Inject constructor(
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