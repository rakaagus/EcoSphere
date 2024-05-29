package com.neirasphere.ecosphere.presentation.screen.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neirasphere.ecosphere.data.repository.AppRepositoryImpl
import com.neirasphere.ecosphere.domain.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(private val appRepository: AppRepository): ViewModel() {

    suspend fun saveStatusOnboarding(status: Boolean){
        viewModelScope.launch {
            appRepository.saveStatusOnboardingUser(status)
        }
    }

}