package com.neirasphere.ecosphere.presentation.screen.profile

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
class ProfileViewModel @Inject constructor(
    private val appRepository: AppRepository
): ViewModel() {
    private val _user = MutableStateFlow(ProfileState())
    val user = _user.asStateFlow()

    fun getUser() = viewModelScope.launch {
        appRepository.getSessionUser().collect{ result ->
            _user.update {
                it.copy(
                    user = result
                )
            }
        }
    }
}