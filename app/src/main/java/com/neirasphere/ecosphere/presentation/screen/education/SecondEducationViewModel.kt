package com.neirasphere.ecosphere.presentation.screen.education

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neirasphere.ecosphere.data.repository.EducationRepositoryImpl
import com.neirasphere.ecosphere.domain.model.SecondEducationData
import com.neirasphere.ecosphere.domain.repository.EducationRepository
import com.neirasphere.ecosphere.presentation.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SecondEducationViewModel @Inject constructor(
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