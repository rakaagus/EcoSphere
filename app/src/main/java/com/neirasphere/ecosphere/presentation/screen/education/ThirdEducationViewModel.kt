package com.neirasphere.ecosphere.presentation.screen.education

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neirasphere.ecosphere.data.repository.EducationRepositoryImpl
import com.neirasphere.ecosphere.domain.model.ThirdEducationData
import com.neirasphere.ecosphere.domain.repository.EducationRepository
import com.neirasphere.ecosphere.presentation.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ThirdEducationViewModel @Inject constructor(
    private val repository: EducationRepository
): ViewModel() {
    private val _thirdEducation : MutableStateFlow<UiState<List<ThirdEducationData>>> = MutableStateFlow(
        UiState.Loading
    )

    val thirdEducation : StateFlow<UiState<List<ThirdEducationData>>>
        get() = _thirdEducation

    fun getThirdEducation(thirdEducationId : Long) {
        viewModelScope.launch {
            _thirdEducation.value = UiState.Loading
            _thirdEducation.value = UiState.Success(listOf(repository.getThirdEduById(thirdEducationId)))

        }
    }
}