package com.neirasphere.ecosphere.ui.screen.education

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neirasphere.ecosphere.data.EducationRepository
import com.neirasphere.ecosphere.domain.model.ThirdEducationData
import com.neirasphere.ecosphere.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ThirdEducationViewModel (
    private val repository: EducationRepository
): ViewModel() {
    private val _thirdEducation : MutableStateFlow<UiState<List<com.neirasphere.ecosphere.domain.model.ThirdEducationData>>> = MutableStateFlow(
        UiState.Loading
    )

    val thirdEducation : StateFlow<UiState<List<com.neirasphere.ecosphere.domain.model.ThirdEducationData>>>
        get() = _thirdEducation

    fun getThirdEducation(thirdEducationId : Long) {
        viewModelScope.launch {
            _thirdEducation.value = UiState.Loading
            _thirdEducation.value = UiState.Success(listOf(repository.getThirdEduById(thirdEducationId)))

        }
    }
}