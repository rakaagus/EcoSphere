package com.neirasphere.ecosphere.presentation.screen.profile.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neirasphere.ecosphere.domain.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor(
    private val appRepository: AppRepository
): ViewModel() {
    private val _state = MutableStateFlow(EditProfileState())
    val state = _state.asStateFlow()

    init {
        getDataUser()
    }

    private fun getDataUser() = viewModelScope.launch {
        appRepository.getSessionUser().collect{result ->
            _state.update {
                it.copy(
                    user = result
                )
            }
        }
    }
}